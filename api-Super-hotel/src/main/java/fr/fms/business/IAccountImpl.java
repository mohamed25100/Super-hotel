package fr.fms.business;

import fr.fms.dao.UserRepository;
import fr.fms.entities.User;
import fr.fms.entities.User.Role;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class IAccountImpl implements IAccount {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        // Hashage du mot de passe avant l'enregistrement
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        log.info("Saving user: {}", user);
        return userRepository.save(user);
    }

    @Override
    public void addRoleToUser(String email, Role role) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(role);
            userRepository.save(user);
            log.info("Role {} assigned to user: {}", role, email);
        } else {
            log.error("User not found: {}", email);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
