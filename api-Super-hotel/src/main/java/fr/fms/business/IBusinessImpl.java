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
}
