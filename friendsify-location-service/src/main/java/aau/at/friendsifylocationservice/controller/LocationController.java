package aau.at.friendsifylocationservice.controller;

import aau.at.friendsifylocationservice.beans.CityDetails;
import aau.at.friendsifylocationservice.beans.Distance;
import aau.at.friendsifylocationservice.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/geo")
public class LocationController {
    @Autowired
    private LocationService service;

    @GetMapping("/{geoDBID}/details")
    public ResponseEntity<CityDetails> getCityDetails(@PathVariable String geoDBID){
        CityDetails details=service.getCityDetails(geoDBID);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/distance")
    public ResponseEntity<Distance> getDistance(@RequestParam String fromGeoDBID, @RequestParam String toGeoDBID) {
        Distance distance= service.getDistance(fromGeoDBID,toGeoDBID);
        return ResponseEntity.ok(distance);
    }

    @GetMapping("/meetingsLocations")
    public ResponseEntity<List<CityDetails>> getMeetingsLocations(@RequestParam String fromGeoDBID, @RequestParam String toGeoDBID) {
        List<CityDetails> cities=service.getMeetingPoints(fromGeoDBID, toGeoDBID);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{geoDBID}/nearbyCities")
    public ResponseEntity<List<CityDetails>> getCitiesNearbyWithMinPopulation(@PathVariable String geoDBID, @RequestParam(required=false, defaultValue="1") long minPopulationCount) {
        List<CityDetails> cities=service.getCitiesNearby(geoDBID,minPopulationCount);
        return ResponseEntity.ok(cities);
    }

   @GetMapping("/{geoDBID}/nearbyBigCities")
    public ResponseEntity<List<CityDetails>> getBigCitiesNearby(@PathVariable String geoDBID){
       List<CityDetails> cities=service.getBigCitiesNearby(geoDBID);
       return ResponseEntity.ok(cities);
    }
}
