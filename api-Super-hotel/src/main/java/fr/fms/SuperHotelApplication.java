package fr.fms;

import fr.fms.business.IAccountImpl;
import fr.fms.business.IBusinessImpl;
import fr.fms.dao.CityRepository;
import fr.fms.dao.HotelRepository;
import fr.fms.dao.RoomRepository;
import fr.fms.dao.UserRepository;
import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import fr.fms.entities.Room;
import fr.fms.entities.User;
import fr.fms.entities.User.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperHotelApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SuperHotelApplication.class, args);
	}

	@Autowired
	private HotelRepository hotelRepository;
/*
	@Autowired
	private UserRepository userRepository;*/

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private IAccountImpl accountService; // Utilisation de IAccountImpl

	@Autowired
	private IBusinessImpl businessService; // Injection de IBusinessImpl

	@Override
	public void run(String... args) throws Exception {
		dataHotel();
	}

	public void dataHotel() {
		// Création des utilisateurs avec IAccountImpl
		User user = new User(null, "John", "Doe", "user@example.com", "password123", Role.USER, null);
		User admin = new User(null, "Admin", "Smith", "admin@example.com", "password123", Role.ADMIN, null);
		User manager = new User(null, "Manager", "One", "manager1@example.com", "password123", Role.MANAGER, null);

		accountService.saveUser(user);
		accountService.saveUser(admin);
		accountService.saveUser(manager);

		// Créer des utilisateurs (managers)
		/*
		User manager1 = new User();
		manager1.setFirstName("Manager");
		manager1.setLastName("One");
		manager1.setPassword("password123");
		manager1.setEmail("manager1@example.com");
		manager1.setRole(User.Role.MANAGER);
		userRepository.save(manager1);

		User manager2 = new User();
		manager2.setFirstName("Manager");
		manager2.setLastName("Two");
		manager2.setPassword("password123");
		manager2.setEmail("manager2@example.com");
		manager2.setRole(User.Role.MANAGER);
		userRepository.save(manager2);
		*/
		// Créer des villes
		/*
		City city1 = new City();
		city1.setName("Paris");
		City city2 = new City();
		city2.setName("Lyon");
		City city3 = new City(null,"Marseille",null);
		cityRepository.save(city1);
		cityRepository.save(city2);
		cityRepository.save(city3);

		 */

		// Ajouter des villes avec businessService
		City city1 = new City(null, "Paris", null);
		City city2 = new City(null, "Lyon", null);
		City city3 = new City(null, "Marseille", null);

		businessService.addCity(city1);
		businessService.addCity(city2);
		businessService.addCity(city3);

		// Mise à jour de la ville Marseille (city3)
		city3.setName("Nice");
		businessService.updateCity(city3); // Mise à jour de la ville avec le nouveau nom

		// Créer des hôtels sans spécifier l'ID (car il sera généré par la base de données)
		Hotel hotel1 = new Hotel();
		hotel1.setName("Hotel Paris");
		hotel1.setAddress("1 rue de Paris");
		hotel1.setPhone("01 23 45 67 89");
		hotel1.setStars(5);
		hotel1.setPricePerNight(150.0);
		hotel1.setCity(city1);
		hotel1.setManager(manager);
		hotel1.setImageUrl("https://www.sowell.fr/wp-content/uploads/2021/02/57-chambre-1270x700px.webp");
		hotelRepository.save(hotel1);

		Hotel hotel2 = new Hotel();
		hotel2.setName("Hotel Lyon");
		hotel2.setAddress("12 rue de Lyon");
		hotel2.setPhone("04 56 78 90 12");
		hotel2.setStars(4);
		hotel2.setPricePerNight(120.0);
		hotel2.setCity(city2);
		hotel2.setManager(manager);
		hotel2.setImageUrl("https://media-cdn.tripadvisor.com/media/photo-s/16/1a/ea/54/hotel-presidente-4s.jpg");
		hotelRepository.save(hotel2);

		// Créer des chambres pour chaque hôtel

		/*
		Room room1 = new Room();
		room1.setRoomNumber("101");
		room1.setType("Double");
		room1.setCapacity(2);
		room1.setPricePerNight(150.0);
		room1.setIsAvailable(true);
		room1.setHotel(hotel1);
		roomRepository.save(room1);

		Room room2 = new Room();
		room2.setRoomNumber("102");
		room2.setType("Single");
		room2.setCapacity(1);
		room2.setPricePerNight(100.0);
		room2.setIsAvailable(true);
		room2.setHotel(hotel1);
		roomRepository.save(room2);

		 */

		Room room3 = new Room();
		room3.setRoomNumber("201");
		room3.setType("Suite");
		room3.setCapacity(4);
		room3.setPricePerNight(250.0);
		room3.setIsAvailable(false); // Chambre non disponible
		room3.setHotel(hotel2);
		roomRepository.save(room3);

		Room room4 = new Room();
		room4.setRoomNumber("202");
		room4.setType("Double");
		room4.setCapacity(2);
		room4.setPricePerNight(120.0);
		room4.setIsAvailable(true);
		room4.setHotel(hotel2);
		roomRepository.save(room4);

		// Afficher un message pour confirmer que les données ont été ajoutées
		System.out.println("Données d'hôtels et chambres ajoutées avec succès!");
	}
}
