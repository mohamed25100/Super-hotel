package fr.fms.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import fr.fms.business.DatabaseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private DatabaseUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT = stateless
                .csrf(csrf -> csrf.disable()) // Désactivation CSRF (nécessaire pour une API REST)
                .cors(Customizer.withDefaults()) // Gestion des CORS
                .authorizeHttpRequests(ahr -> {
                    ahr.requestMatchers(HttpMethod.GET, "/**").permitAll();
                    ahr.requestMatchers(HttpMethod.POST, "/**").permitAll();
                    ahr.requestMatchers(HttpMethod.PUT, "/**").permitAll();
                    ahr.requestMatchers(HttpMethod.DELETE, "/**").permitAll();
                    ahr.anyRequest().authenticated(); // Toutes les autres requêtes nécessitent un token valide
                })
                .oauth2ResourceServer(ors -> ors.jwt(jwt -> jwt.jwtAuthenticationConverter(authenticationConverter()))) // Gestion des JWT
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("jwt-secret.txt").toURI());
        String secretKey = Files.readString(path);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    private JwtAuthenticationConverter authenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        // Convertir le champ 'scope' en autorités (rôles)
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("ROLE_");  // Préfixe 'ROLE_' pour les rôles
        authoritiesConverter.setAuthoritiesClaimName("scope");  // Le champ 'scope' dans le token contient les rôles

        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return converter;
    }

    @Bean
    public JwtEncoder jwtEncoder() throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("jwt-secret.txt").toURI());
        String secretKey = Files.readString(path);
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
    }

    // Nouvelle méthode pour authentifier l'utilisateur et loguer ses rôles
    private Authentication authenticateAndLog(Authentication authentication) {
        // Authentifie l'utilisateur
        Authentication authenticated = authenticationManager(userDetailsService).authenticate(authentication);

        // Logge les rôles de l'utilisateur
        log.info("User roles from JWT: {}", authenticated.getAuthorities());

        return authenticated;
    }
}
