package fr.fms;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.CityRepository;
import fr.fms.entities.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class IAccountImplTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private IBusinessImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCity() {
        City city = new City();
        city.setName("Paris");

        when(cityRepository.save(city)).thenReturn(city);

        City savedCity = accountService.addCity(city);

        assertNotNull(savedCity);
        assertEquals("Paris", savedCity.getName());
        verify(cityRepository, times(1)).save(city);
    }

    @Test
    void testAddCityWithNullName() {
        City city = new City();

        when(cityRepository.save(city)).thenReturn(city);

        assertThrows(IllegalArgumentException.class, () -> accountService.addCity(city));
        verify(cityRepository, never()).save(city);
    }
}
