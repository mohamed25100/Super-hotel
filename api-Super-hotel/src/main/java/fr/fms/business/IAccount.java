package fr.fms.business;

import fr.fms.entities.User;
import fr.fms.entities.User.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAccount {
    User saveUser(User user);

    void addRoleToUser(String email, Role role);

    User findUserByEmail(String email);

    ResponseEntity<List<User>> listUsers();
}
