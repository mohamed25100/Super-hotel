package fr.fms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "T_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Utilisateur ayant effectué la réservation

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel; // Hôtel concerné par la réservation

    @ManyToMany
    @JoinTable(
            name = "T_booking_rooms",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Room> rooms; // Liste des chambres réservées

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate; // Date d'arrivée

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate; // Date de départ

    @Column(name = "total_price", nullable = false)
    private Double totalPrice; // Prix total de la réservation

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status; // Statut de la réservation (PENDING, CONFIRMED, CANCELLED)

    // Enum pour gérer le statut de la réservation
    public enum BookingStatus {
        PENDING, CONFIRMED, CANCELLED
    }
}
