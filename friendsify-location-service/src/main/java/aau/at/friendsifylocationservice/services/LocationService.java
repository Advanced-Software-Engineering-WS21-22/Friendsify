package aau.at.friendsifylocationservice.services;

import aau.at.friendsifylocationservice.beans.CitiesNearby;
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


    public List<CityDetails> getMeetingPoints(String fromCityWikiDataID, String toCityWikiDataID) {
        List<CityDetails> citiesList= new ArrayList<>();
        Distance distance = this.getDistance(fromCityWikiDataID, toCityWikiDataID);
        if (distance.getDistanceInKM()<=100){
            CityDetails details=getCityDetails(toCityWikiDataID);
            citiesList.addAll(this.getCitiesNearby(fromCityWikiDataID,details.getData().getPopulation()));
        }else{
            citiesList.addAll(this.getBigCitiesNearby(fromCityWikiDataID));
            citiesList.addAll(this.getBigCitiesNearby(toCityWikiDataID));
        }

        return citiesList;
    }

    public List<CityDetails> getCitiesNearby(String geoDBID, long minPopulation) {
        return client.getCitiesNearby(geoDBID,minPopulation).getData();
    }

    public List<CityDetails> getBigCitiesNearby(String geoDBID) {
        return this.getCitiesNearby(geoDBID,10000);
    }


}
