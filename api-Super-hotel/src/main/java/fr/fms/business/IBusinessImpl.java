package fr.fms.business;

import fr.fms.dao.CityRepository;
import fr.fms.dao.HotelRepository;
import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class IBusinessImpl implements IBusiness {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<Hotel> getHotels() {
        return hotelRepository.findAll(); // Retourne la liste de tous les hôtels
    }
    @Override
    public List<City> getCities() {
        return cityRepository.findAll();
    }
    @Override
    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }
    @Override
    public void deleteHotel(Long id) {
        if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id);
            log.info("Hôtel avec l'ID {} supprimé avec succès", id);
        } else {
            log.warn("Tentative de suppression d'un hôtel inexistant (ID: {})", id);
        }
    }

    @Override
    public void deleteCity(Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            log.info("Ville avec l'ID {} supprimée avec succès", id);
        } else {
            log.warn("Tentative de suppression d'une ville inexistante (ID: {})", id);
            throw new RuntimeException("Ville introuvable");
        }
    }

    // 🔹 Ajouter une ville
    @Override
    public City addCity(City city) {
        if (city == null || city.getName() == null || city.getName().isEmpty()) {
            log.error("Tentative d'ajout d'une ville avec des informations manquantes");
            throw new IllegalArgumentException("La ville doit avoir un nom valide");
        }

        try {
            // Sauvegarde la ville et retourne l'objet city après sa persistance
            City savedCity = cityRepository.save(city);
            cityRepository.flush(); // Forcer la synchronisation de la session Hibernate
            log.info("Ville ajoutée avec succès : {}", savedCity.getName());
            return savedCity;
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout de la ville", e);
            throw new RuntimeException("Erreur lors de l'ajout de la ville", e);
        }
    }

    @Override
    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public City updateCity(City city) {
        if (city == null || city.getId() == null || city.getId() <= 0) {
            log.error("Tentative de mise à jour avec un ID invalide : {}", (city != null ? city.getId() : "null"));
            throw new IllegalArgumentException("L'ID de la ville doit être valide et supérieur à 0");
        }

        Optional<City> existingCity = cityRepository.findById(city.getId());
        if (existingCity.isPresent()) {
            City updatedCity = cityRepository.save(city);
            log.info("Ville mise à jour avec succès : {}", updatedCity.getName());
            return updatedCity;
        } else {
            log.warn("Tentative de mise à jour d'une ville inexistante (ID: {})", city.getId());
            throw new RuntimeException("Ville introuvable avec l'ID : " + city.getId());
        }
    }

    // 🔹 Récupérer tous les hôtels associés à une ville par son ID
    @Override
    public List<Hotel> getHotelsByCityId(Long cityId) {
        // Recherche des hôtels par l'ID de la ville
        List<Hotel> hotels = hotelRepository.findByCityId(cityId);
        if (hotels.isEmpty()) {
            log.warn("Aucun hôtel trouvé pour la ville avec l'ID : {}", cityId);
        } else {
            log.info("Hôtels trouvés pour la ville avec l'ID : {}", cityId);
        }
        return hotels;
    }


}
