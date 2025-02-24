package fr.fms.dao;

import fr.fms.entities.Booking;
import fr.fms.entities.Hotel;
import fr.fms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user); // Trouver toutes les réservations d'un utilisateur

    List<Booking> findByHotel(Hotel hotel); // Trouver toutes les réservations pour un hôtel spécifique

    List<Booking> findByUserAndHotel(User user, Hotel hotel); // Trouver les réservations d'un utilisateur pour un hôtel donné

}
