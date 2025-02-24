package fr.fms.business;

import fr.fms.dao.HotelRepository;
import fr.fms.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBusinessImpl implements IBusiness {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getHotels() {
        return hotelRepository.findAll(); // Retourne la liste de tous les hôtels
    }
}
