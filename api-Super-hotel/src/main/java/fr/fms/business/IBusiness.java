package fr.fms.business;

import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import fr.fms.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBusiness {
    /**
     * Récupère la liste de tous les hôtels.
     *
     * @return une liste d'objets {@link Hotel}.
     */
    List<Hotel> getHotels();

    /**
     * Récupère la liste de toutes les villes.
     *
     * @return une liste d'objets {@link City}.
     */
    List<City> getCities();

    /**
     * Récupère un hôtel par son identifiant.
     *
     * @param id l'identifiant de l'hôtel.
     * @return un objet {@link Optional} contenant l'hôtel s'il existe, sinon un Optional vide.
     */
    Optional<Hotel> getHotelById(Long id);

    /**
     * Supprime un hôtel par son identifiant.
     *
     * @param id l'identifiant de l'hôtel à supprimer.
     */
    void deleteHotel(Long id);

    /**
     * Supprime une ville par son identifiant.
     *
     * @param id l'identifiant de la ville à supprimer.
     */
    void deleteCity(Long id);

    /**
     * Ajoute une nouvelle ville.
     *
     * @param city l'objet {@link City} à ajouter.
     * @return la ville ajoutée avec son identifiant généré.
     */
    City addCity(City city);

    /**
     * Récupère une ville par son identifiant.
     *
     * @param id l'identifiant de la ville.
     * @return un objet {@link Optional} contenant la ville si elle existe, sinon un Optional vide.
     */
    Optional<City> getCityById(Long id);

    /**
     * Met à jour une ville existante.
     *
     * @param city l'objet {@link City} contenant les nouvelles informations.
     * @return l'objet {@link City} mis à jour.
     */
    City updateCity(City city);

    /**
     * Récupère tous les hôtels associés à une ville donnée par son identifiant.
     *
     * @param cityId l'identifiant de la ville.
     * @return une liste d'objets {@link Hotel} appartenant à cette ville.
     */
    List<Hotel> getHotelsByCityId(Long cityId);

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return une liste d'objets {@link User}.
     */
    List<User> getUsers();

    /**
     * Ajoute un nouvel hôtel.
     *
     * @param hotel l'objet {@link Hotel} à ajouter.
     * @return l'hôtel ajouté avec son identifiant généré.
     */
    Hotel addHotel(Hotel hotel);
}
