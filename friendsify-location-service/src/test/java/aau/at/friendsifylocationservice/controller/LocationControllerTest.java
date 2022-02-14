package aau.at.friendsifylocationservice.controller;

import aau.at.friendsifylocationservice.beans.CitiesNearby;
import aau.at.friendsifylocationservice.beans.City;
import aau.at.friendsifylocationservice.beans.CityDetails;
import aau.at.friendsifylocationservice.beans.Distance;
import aau.at.friendsifylocationservice.services.LocationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LocationController.class)
class LocationControllerTest {

    @MockBean
    private LocationService service;

    @Autowired
    private MockMvc mockMvc;

    City cityKlagenfurt;
    City cityVillach;
    City cityVienna;
    City cityGraz;
    CityDetails detailsKlagenfurt;
    CityDetails detailsVillach;
    CitiesNearby nearbies;
    Distance distanceKV;
    Distance distanceVV;
    List<City> cities;
    List<City> cities2;

    static final int DISTANCE_KLAGENFURT_VILLACH_KM = 22;
    static final int DISTANCE_Vienna_VILLACH_KM = 338;
    static final int VILLACH_POPULATION = 61879;
    static final int DEFAULT_MIN_POPULATION = 1;

    @BeforeEach
    void setUp() {
        cityKlagenfurt = new City(132768,"Q41753", "CITY", "Klagenfurt", "Klagenfurt", "Austria", "AT", "Carinthia", "2", 446, 46.616666666, 14.3, 101403, "Europe__Vienna");
        cityVillach = new City(132719, "Q483522", "CITY", "Villach", "Villach", "Austria", "AT", "Carinthia", "2", 501, 46.616666666, 13.833333333, 61879, "Europe__Vienna");
        cityVienna = new City(3453053, "Q1741", "CITY","Vienna","Vienna","Austria","AT","Vienna","9",171,48.208333333,16.3725,1911191,"Europe__Vienna");
        cityGraz = new City(132781,"Q13298","CITY","Graz","Graz","Austria","AT","Styria", "6", (Integer) null,47.070833333,15.438611111,289440, "Europe__Vienna");
        detailsKlagenfurt = new CityDetails(cityKlagenfurt);
        detailsVillach = new CityDetails(cityVillach);
        cities = new ArrayList<>();
        cities2 = new ArrayList<>();
        distanceKV = new Distance(DISTANCE_KLAGENFURT_VILLACH_KM);
        distanceVV = new Distance(DISTANCE_Vienna_VILLACH_KM);
    }

    @AfterEach
    void tearDown() {
        cityKlagenfurt = null;
        cityVillach = null;
        cityVienna = null;
        cityGraz = null;
        cities = null;
        cities2 = null;
        detailsKlagenfurt = null;
        nearbies = null;
        distanceKV = null;
        distanceVV = null;
    }

    @Test
    void getCityDetails() {
        when(service.getCityDetails("Q41753")).thenReturn(detailsKlagenfurt);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/geo/Q41753/details")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.id", is(cityKlagenfurt.getId())));
        } catch (Exception e) {
            fail("Should have found details!");
        }
    }

    @Test
    void getDistance() {
        when(service.getDistance("Q41753", "Q483522")).thenReturn(distanceKV);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/geo/distance?fromGeoDBID=Q41753&toGeoDBID=Q483522")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data", is(DISTANCE_KLAGENFURT_VILLACH_KM)));
        } catch (Exception e) {
            fail("Should have found the distance!");
        }
    }

    @Test
    void getMeetingPointsCitiesWithinDistance() {
        cities.add(cityKlagenfurt);
        cities.add(cityVillach);
        when(service.getMeetingPoints("Q41753", "Q483522")).thenReturn(cities);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/geo/meetingsLocations?fromGeoDBID=Q41753&toGeoDBID=Q483522")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id", is(cityKlagenfurt.getId())))
                    .andExpect(jsonPath("$[1].id", is(cityVillach.getId())));
        } catch (Exception e) {
            fail("Should have found meeting points!");
        }
    }

    @Test
    void getMeetingPointsCitiesTooFarAway() {
        cities.add(cityGraz);
        cities.add(cityKlagenfurt);
        when(service.getMeetingPoints("Q1741", "Q483522")).thenReturn(cities);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/geo/meetingsLocations?fromGeoDBID=Q1741&toGeoDBID=Q483522")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id", is(cityGraz.getId())))
                    .andExpect(jsonPath("$[1].id", is(cityKlagenfurt.getId())));
        } catch (Exception e) {
            fail("Should have found meeting points!");
        }
    }

    @Test
    void getCitiesNearbyWithDefaultMinPopulation() {
        cities.add(cityVillach);
        when(service.getCitiesNearby("Q41753", DEFAULT_MIN_POPULATION)).thenReturn(cities);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/geo/Q41753/nearbyCities")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id", is(cityVillach.getId())));
        } catch (Exception e) {
            fail("Should have found cities nearby!");
        }
    }

    @Test
    void getCitiesNearbyWithMinPopulation() {
        cities.add(cityVillach);
        when(service.getCitiesNearby("Q41753", VILLACH_POPULATION)).thenReturn(cities);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/geo/Q41753/nearbyCities?minPopulationCount="+VILLACH_POPULATION)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id", is(cityVillach.getId())));
        } catch (Exception e) {
            fail("Should have found cities nearby!");
        }
    }

    @Test
    void getBigCitiesNearby() {
        cities.add(cityVillach);
        when(service.getBigCitiesNearby("Q41753")).thenReturn(cities);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/geo/Q41753/nearbyBigCities")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id", is(cityVillach.getId())));
        } catch (Exception e) {
            fail("Should have found cities nearby!");
        }
    }
}