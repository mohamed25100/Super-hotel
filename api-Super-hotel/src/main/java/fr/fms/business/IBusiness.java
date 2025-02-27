package fr.fms.business;

import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import fr.fms.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBusiness {
    List<Hotel> getHotels(); // Récupérer la liste de tous les hôtels
    List<City> getCities();  // Récupérer la liste de toutes les villes
    Optional<Hotel> getHotelById(Long id); // Récupérer un hôtel par son ID
    void deleteHotel(Long id); // Supprimer un hôtel par son ID
    void deleteCity(Long id); // Supprimer une ville par son ID
    City addCity(City city);  // Ajouter une ville
    Optional<City> getCityById(Long id); // Récupérer une ville par son ID
    City updateCity(City city); // Mettre à jour une ville
    List<Hotel> getHotelsByCityId(Long cityId); // Récupérer les hôtels d'une ville par ID
    List<User> getUsers();// Récupérer la liste de tous les utilisateurs
    Hotel addHotel(Hotel hotel);  // Ajouter un hôtel
}
