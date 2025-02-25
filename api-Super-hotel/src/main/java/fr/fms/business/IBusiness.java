package fr.fms.business;

import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBusiness {
    List<Hotel> getHotels(); // Récupérer la liste de tous les hôtels
    List<City> getCities();  // Récupérer la liste de toutes les villes
}
