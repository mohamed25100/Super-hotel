package fr.fms.dao;

import fr.fms.entities.Room;
import fr.fms.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotel(Hotel hotel); // Trouver toutes les chambres d'un hôtel donné
/*
    List<Room> findByAvailableTrue(); // Trouver toutes les chambres disponibles

    List<Room> findByHotelAndAvailableTrue(Hotel hotel); // Trouver les chambres disponibles d'un hôtel spécifique
*/
}
