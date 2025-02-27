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


}
