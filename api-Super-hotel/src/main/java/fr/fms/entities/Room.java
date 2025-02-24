package fr.fms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "T_rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number", nullable = false)
    private String roomNumber; // Numéro de la chambre (peut être un identifiant unique pour chaque chambre)

    @Column(name = "type", nullable = false)
    private String type; // Type de chambre (ex : Simple, Double, Suite, etc.)

    @Column(name = "capacity")
    private Integer capacity; // Capacité de la chambre (nombre de personnes)

    @Column(name = "price_per_night")
    private Double pricePerNight; // Prix par nuit pour cette chambre

    @Column(name = "is_available")
    private Boolean isAvailable; // Statut de disponibilité de la chambre

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel; // L'hôtel auquel cette chambre appartient
}
