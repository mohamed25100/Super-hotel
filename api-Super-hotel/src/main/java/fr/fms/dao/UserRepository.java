package fr.fms.dao;

import fr.fms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // Trouver un utilisateur par email

    boolean existsByEmail(String email); // Vérifier si un email est déjà utilisé
}