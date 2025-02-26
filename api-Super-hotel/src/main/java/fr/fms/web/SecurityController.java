package fr.fms.web;

import fr.fms.entities.User;
import fr.fms.security.UserPrincipal;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@Transactional
@RequestMapping("/api")
public class SecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Endpoint pour l'authentification des utilisateurs.
     * L'utilisateur soumet son email et mot de passe.
     * En cas de succès, un JWT est généré et retourné.
     * @param credentials (email, password)
     * @return JWT token en cas de succès
     */
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Tentative d'authentification avec email au lieu de username
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        System.out.println("Utilisateur authentifié : " + authentication.getPrincipal());

        // Récupération des rôles
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        // Récupération des informations de l'utilisateur
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal(); // Correct cast
        User user = userPrincipal.getUser(); // Accède directement à l'objet User

        // Génération du JWT
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                .subject(email)
                .claim("id", user.getId()) // Ajout de l'ID de l'utilisateur dans les claims du JWT
                .claim("firstName", user.getFirstName()) // Ajout du prénom de l'utilisateur dans les claims du JWT
                .claim("lastName", user.getLastName()) // Ajout du nom de famille de l'utilisateur dans les claims du JWT
                .claim("scope", scope)
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters
                .from(JwsHeader.with(MacAlgorithm.HS512).build(), jwtClaimsSet);

        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();

        return Map.of("access-token", jwt);
    }

    /**
     * Endpoint pour récupérer les informations de l'utilisateur connecté
     * @param authentication Informations de l'utilisateur
     * @return Authentication (détails de l'utilisateur)
     */
    @GetMapping("/infos")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }
}
