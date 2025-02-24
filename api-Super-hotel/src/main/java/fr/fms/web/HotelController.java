package fr.fms.web;

import fr.fms.business.IBusiness;
import fr.fms.entities.Hotel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
