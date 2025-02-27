package fr.fms.dao;

import fr.fms.entities.Hotel;
import fr.fms.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByCity(City city); // Trouver les hôtels d'une ville donnée

    List<Hotel> findByNameContainingIgnoreCase(String name); // Recherche d'hôtels par nom (insensible à la casse)

    boolean existsById(Long id); // Vérifie si un hôtel existe par son ID

    void deleteById(Long id); // Supprime un hôtel par son ID

    List<Hotel> findByCityId(Long cityId); // Recherche des hôtels par l'ID de la ville
}
