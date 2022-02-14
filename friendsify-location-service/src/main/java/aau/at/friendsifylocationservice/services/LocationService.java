package aau.at.friendsifylocationservice.services;

import aau.at.friendsifylocationservice.beans.CitiesNearby;
import aau.at.friendsifylocationservice.beans.City;
import aau.at.friendsifylocationservice.beans.CityDetails;
import aau.at.friendsifylocationservice.beans.Distance;
import aau.at.friendsifylocationservice.services.ExternalAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
    @Autowired
    private ExternalAPIClient client;

    public CityDetails getCityDetails(String geoDBID) {
        return client.getCityDetails(geoDBID);
    }

    public Distance getDistance(String fromCityWikiDataID, String toCityWikiDataID) {
        return client.getDistanceBetweenTwoCities(fromCityWikiDataID, toCityWikiDataID);
    }


    public List<City> getMeetingPoints(String fromCityWikiDataID, String toCityWikiDataID) {
        List<City> citiesList= new ArrayList<>();
        Distance distance = this.getDistance(fromCityWikiDataID, toCityWikiDataID);
        try {
            Thread.sleep(2000);
            if (distance.getDistanceInKM()<=100){
                CityDetails details=getCityDetails(toCityWikiDataID);
                Thread.sleep(2000);
                citiesList.addAll(this.getCitiesNearby(fromCityWikiDataID,details.getData().getPopulation()));
            }else{
                citiesList.addAll(this.getBigCitiesNearby(fromCityWikiDataID));
                Thread.sleep(2000);
                citiesList.addAll(this.getBigCitiesNearby(toCityWikiDataID));
            }

        } catch (InterruptedException e) {
            System.out.println("Only 1 request per second allowed - timeout was stopped.");
            Thread.currentThread().interrupt();
        }
        return citiesList;
    }

    public List<City> getCitiesNearby(String geoDBID, long minPopulation) {
        return client.getCitiesNearby(geoDBID,minPopulation).getData();
    }

    public List<City> getBigCitiesNearby(String geoDBID) {
        return this.getCitiesNearby(geoDBID,10000);
    }


}
