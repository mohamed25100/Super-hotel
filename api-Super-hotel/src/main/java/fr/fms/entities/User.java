package fr.fms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName; // Prénom de l'utilisateur

    @Column(name = "last_name", nullable = false)
    private String lastName; // Nom de famille de l'utilisateur

    @Column(name = "email", nullable = false, unique = true)
    private String email; // Email de l'utilisateur

    @Column(name = "password", nullable = false)
    private String password; // Mot de passe de l'utilisateur (stocké sous forme de hash)

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role; // Rôle de l'utilisateur (ex : ADMIN, USER, MANAGER)

    // Relation avec les hôtels (si l'utilisateur est un gestionnaire d'hôtel)
    @OneToMany(mappedBy = "manager")
    @JsonIgnore
    private List<Hotel> hotelsManaged; // Liste des hôtels gérés par l'utilisateur s'il est un manager

    // Enum pour les rôles de l'utilisateur
    public enum Role {
        ADMIN, USER, MANAGER // ADMIN a des droits complets, USER a des droits limités, MANAGER gère des hôtels
    }
}
