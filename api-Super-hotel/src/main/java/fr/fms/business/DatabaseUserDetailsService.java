package fr.fms.business;

import fr.fms.dao.UserRepository;
import fr.fms.entities.User;
import fr.fms.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(DatabaseUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("🔍 Tentative de chargement de l'utilisateur avec l'email: {}", email);

        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> {
            logger.error("❌ Utilisateur non trouvé avec l'email: {}", email);
            return new UsernameNotFoundException("Utilisateur non trouvé: " + email);
        });

        logger.info("✅ Utilisateur authentifié : {} avec rôle: {}", user.getEmail(), user.getRole());

        return new UserPrincipal(user);
    }
}
