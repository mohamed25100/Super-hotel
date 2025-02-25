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
@Table(name = "T_hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "stars")
    private Integer stars; // Nombre d'étoiles (par exemple : 1 à 5)

    @Column(name = "price_per_night")
    private Double pricePerNight; // Prix par nuit

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city; // Ville à laquelle cet hôtel appartient

    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
    private List<Room> rooms; // Liste des chambres de l'hôtel

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings; // Liste des réservations effectuées pour cet hôtel

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private User manager; // Le responsable (Manager) de l'hôtel

    @Column(name = "image_url")
    private String imageUrl; // URL de l'image de l'hôtel

}
