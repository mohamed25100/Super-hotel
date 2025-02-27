package fr.fms;

import fr.fms.business.IAccountImpl;
import fr.fms.dao.CityRepository;
import fr.fms.dao.HotelRepository;
import fr.fms.dao.RoomRepository;
import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import fr.fms.entities.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class SuperHotelApplicationTest {

	@Mock
	private HotelRepository hotelRepository;

	@Mock
	private CityRepository cityRepository;

	@Mock
	private RoomRepository roomRepository;

	@Mock
	private IAccountImpl accountService;

	@InjectMocks
	private SuperHotelApplication superHotelApplication;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testDataHotel() {
		// Préparer les objets de test
		City city1 = new City();
		city1.setName("Paris");
		City city2 = new City();
		city2.setName("Lyon");

		Hotel hotel1 = new Hotel();
		hotel1.setName("Hotel Paris");

		Hotel hotel2 = new Hotel();
		hotel2.setName("Hotel Lyon");

		Room room1 = new Room();
		room1.setRoomNumber("101");
		room1.setHotel(hotel1);

		Room room2 = new Room();
		room2.setRoomNumber("201");
		room2.setHotel(hotel2);

		// Simuler le comportement des méthodes save
		when(cityRepository.save(city1)).thenReturn(city1);
		when(cityRepository.save(city2)).thenReturn(city2);
		when(hotelRepository.save(hotel1)).thenReturn(hotel1);
		when(hotelRepository.save(hotel2)).thenReturn(hotel2);
		when(roomRepository.save(room1)).thenReturn(room1);
		when(roomRepository.save(room2)).thenReturn(room2);

		// Exécuter la méthode
		superHotelApplication.dataHotel();

		// Vérifier si les méthodes save ont été appelées correctement
		verify(cityRepository, times(2)).save(any(City.class));
		verify(hotelRepository, times(2)).save(any(Hotel.class));
		verify(roomRepository, times(2)).save(any(Room.class));
	}
}
