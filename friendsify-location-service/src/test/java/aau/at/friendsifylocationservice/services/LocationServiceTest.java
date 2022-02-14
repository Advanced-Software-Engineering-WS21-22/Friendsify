package aau.at.friendsifylocationservice.services;

import aau.at.friendsifylocationservice.beans.CitiesNearby;
import aau.at.friendsifylocationservice.beans.City;
import aau.at.friendsifylocationservice.beans.CityDetails;
import aau.at.friendsifylocationservice.beans.Distance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    ExternalAPIClient client;

    @InjectMocks
    LocationService service;

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
    static final int DEFAULT_BIG_CITY_POPULATION = 10000;

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
        when(client.getCityDetails("Q41753")).thenReturn(detailsKlagenfurt);
        assertEquals(detailsKlagenfurt, service.getCityDetails("Q41753"));
    }

    @Test
    void getDistance() {
        when(client.getDistanceBetweenTwoCities("Q41753", "Q483522")).thenReturn(distanceKV);
        assertEquals(distanceKV, service.getDistance("Q41753", "Q483522"));
    }

    @Test
    void getMeetingPointsCitiesWithinDistance() {
        cities.add(cityKlagenfurt);
        cities.add(cityVillach);
        nearbies = new CitiesNearby(cities);
        when(client.getDistanceBetweenTwoCities("Q41753", "Q483522")).thenReturn(distanceKV);
        when(client.getCityDetails("Q483522")).thenReturn(detailsVillach);
        when(client.getCitiesNearby("Q41753", VILLACH_POPULATION)).thenReturn(nearbies);
        assertEquals(nearbies.getData(), service.getMeetingPoints("Q41753", "Q483522"));

    }

    @Test
    void getMeetingPointsCitiesTooFarAway() {
        when(client.getDistanceBetweenTwoCities("Q1741", "Q483522")).thenReturn(distanceVV);
        cities.add(cityKlagenfurt);
        nearbies = new CitiesNearby(cities);
        when(client.getCitiesNearby("Q483522", DEFAULT_BIG_CITY_POPULATION)).thenReturn(nearbies);
        cities2.add(cityGraz);
        nearbies = new CitiesNearby(cities2);
        when(client.getCitiesNearby("Q1741", DEFAULT_BIG_CITY_POPULATION)).thenReturn(nearbies);

        List<City> points=new ArrayList<>();
        points.add(cityGraz);
        points.add(cityKlagenfurt);
        assertEquals(points, service.getMeetingPoints("Q1741", "Q483522"));

    }

    @Test
    void getCitiesNearby() {
        cities.add(cityKlagenfurt);
        cities.add(cityVillach);
        nearbies = new CitiesNearby(cities);
        when(client.getCitiesNearby("Q41753", VILLACH_POPULATION)).thenReturn(nearbies);
        assertEquals(nearbies.getData(), service.getCitiesNearby("Q41753", VILLACH_POPULATION));
    }

    @Test
    void getBigCitiesNearby() {
        cities.add(cityKlagenfurt);
        cities.add(cityVillach);
        nearbies = new CitiesNearby(cities);
        when(client.getCitiesNearby("Q41753", DEFAULT_BIG_CITY_POPULATION)).thenReturn(nearbies);
        assertEquals(nearbies.getData(), service.getBigCitiesNearby("Q41753"));
    }
}