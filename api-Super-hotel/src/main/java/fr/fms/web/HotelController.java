package fr.fms.web;

import fr.fms.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.fms.business.IBusiness;
import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // 🔹 Ajouter une ville
    @PostMapping("/city")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        if (city == null || city.getName() == null || city.getName().isEmpty()) {
            log.error("Erreur : la ville n'a pas de nom ou la requête est mal formée");
            return ResponseEntity.badRequest().build(); // Retourne un code 400 si la ville est invalide
        }
        try {
            log.info("Ajout de la ville : {}", city.getName());
            City city1 = new City(null, city.getName(), null);
            City savedCity = iBusiness.addCity(city1);  // Appel à la méthode du service pour ajouter la ville
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCity); // Retourne un code 201 avec la ville sauvegardée
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout de la ville : {}", city.getName(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retourne un code 500 en cas d'erreur serveur
        }
    }

    // ** Récupérer une ville par son ID **
    @GetMapping("/city/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        Optional<City> city = iBusiness.getCityById(id);  // Recherche de la ville par son ID
        if (city.isPresent()) {
            return ResponseEntity.ok(city.get());  // Retourne la ville trouvée avec un code 200
        } else {
            return ResponseEntity.notFound().build();  // Retourne un code 404 si la ville n'est pas trouvée
        }
    }

    // 🔹 Mettre à jour une ville
    @PutMapping("/city/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City city) {
        log.info("Mise à jour de la ville avec l'ID : {}", id);

        if (city == null || city.getName() == null || city.getName().isEmpty()) {
            log.error("Erreur : les données de la ville sont invalides");
            return ResponseEntity.badRequest().build();
        }

        try {
            city.setId(id); // S'assurer que l'ID correspond à celui dans l'URL
            City updatedCity = iBusiness.updateCity(city);
            return ResponseEntity.ok(updatedCity);
        } catch (RuntimeException e) {
            log.warn("Ville avec l'ID {} introuvable", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Méthode pour récupérer tous les utilisateurs via la couche métier
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return iBusiness.getUsers();  // Appel de la méthode dans IBusinessImpl
    }

    // 🔹 Ajouter un nouvel hôtel
    @PostMapping("/hotel")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        if (hotel == null || hotel.getName() == null || hotel.getName().isEmpty()) {
            log.error("Erreur : l'hôtel n'a pas de nom ou la requête est mal formée");
            return ResponseEntity.badRequest().build(); // Retourne un code 400 si l'hôtel est invalide
        }

        try {
            log.info("Ajout de l'hôtel : {}", hotel.getName());

            // Si l'hôtel n'a pas de manager, on ne l'associe pas
            if (hotel.getManager() == null) {
                log.info("Aucun manager associé à cet hôtel.");
            }

            // Enregistrer l'hôtel sans manager
            Hotel hotel1 = new Hotel();
            hotel1.setName(hotel.getName());
            hotel1.setAddress(hotel.getAddress());
            hotel1.setPhone(hotel.getPhone());
            hotel1.setStars(hotel.getStars());
            hotel1.setPricePerNight(hotel.getPricePerNight());
            hotel1.setCity(hotel.getCity());

            Hotel savedHotel = iBusiness.addHotel(hotel1);  // Appel à la méthode du service pour ajouter l'hôtel

            return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel); // Retourne un code 201 avec l'hôtel sauvegardé
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout de l'hôtel : {}", hotel.getName(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retourne un code 500 en cas d'erreur serveur
        }
    }

}
