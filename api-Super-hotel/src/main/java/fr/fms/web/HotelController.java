package fr.fms.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.fms.business.IBusiness;
import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@Transactional
@RequestMapping("/api")
public class HotelController {

    private static final Logger log = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private IBusiness iBusiness;  // Utilisation du service IBusiness au lieu du repository

    // Méthode pour récupérer tous les hôtels via la couche métier
    @GetMapping("/hotels")
    public List<Hotel> getAllHotels() {
        return iBusiness.getHotels();  // Appel de la méthode dans IBusinessImpl
    }

    // Méthode pour récupérer toutes les villes via la couche métier
    @GetMapping("/cities")
    public List<City> getAllCities() {
        return iBusiness.getCities();  // Appel de la méthode dans IBusinessImpl
    }

    // Récupérer un hôtel par son ID
    @GetMapping("/hotel/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        Optional<Hotel> hotel = iBusiness.getHotelById(id);
        return hotel.orElse(null); // Retourne null si l'hôtel n'existe pas
    }

    // 🔹 Supprimer un hôtel par son ID
    @DeleteMapping("/hotel/{id}")
    public void deleteHotel(@PathVariable Long id) {
        try {
            log.info("Deleting hotel with ID: {}", id);
            iBusiness.deleteHotel(id);
        } catch (Exception e) {
            log.error("Error deleting hotel with ID: {}", id, e);
            throw new RuntimeException("Erreur lors de la suppression de l'hôtel", e);
        }
    }

    // 🔹 Supprimer une ville par son ID
    @DeleteMapping("/city/{id}")
    public void deleteCity(@PathVariable Long id) {
        try {
            log.info("Suppression de la ville avec l'ID : {}", id);
            iBusiness.deleteCity(id);
        } catch (Exception e) {
            log.error("Erreur lors de la suppression de la ville avec l'ID : {}", id, e);
            throw new RuntimeException("Erreur lors de la suppression de la ville", e);
        }
    }


}
