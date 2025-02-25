package fr.fms.web;

import fr.fms.business.IBusiness;
import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@Transactional
@RequestMapping("/api")
public class HotelController {

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
}
