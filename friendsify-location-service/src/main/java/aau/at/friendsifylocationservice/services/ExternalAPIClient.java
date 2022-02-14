package aau.at.friendsifylocationservice.services;

import aau.at.friendsifylocationservice.beans.CitiesNearby;
import aau.at.friendsifylocationservice.beans.CityDetails;
import aau.at.friendsifylocationservice.beans.Distance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExternalAPIClient {

    private final WebClient geoApiClient;

    private final String CITIES="cities";

    @Autowired
    public ExternalAPIClient(
    @Value("${geo.api.url}") String geoApiUrl,
    @Value("${geo.api.host}") String geoApiHost,
    @Value("${geo.api.token}") String token
    ) {
        this.geoApiClient = WebClient.builder()
                .baseUrl(geoApiUrl)
                .defaultHeader("x-rapidapi-host", geoApiHost)
                .defaultHeader("x-rapidapi-key", token)
                .build();
    }

    public CitiesNearby getCitiesNearby(String cityWikiDataID, long populationMin) {
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("radius","100");
        params.add("limit","10");
        params.add("minPopulation", Long.toString(populationMin));
        params.add("distanceUnit", "KM");
        params.add("types", "CITY");


         CitiesNearby response= this.geoApiClient
                .get()
                .uri(builder ->builder.path("/"+CITIES+"/"+cityWikiDataID+"/nearbyCities").queryParams(params).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                 .bodyToMono(CitiesNearby.class)
                 .block();

        return response;
    }

    public Distance getDistanceBetweenTwoCities(String fromCityWikiDataID, String toCityWikiDataID) {
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("fromCityId",fromCityWikiDataID);
        params.add("distanceUnit", "KM");


         Distance response= this.geoApiClient
                .get()
                .uri(builder ->builder.path("/"+CITIES+"/"+toCityWikiDataID+"/distance").queryParams(params).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                 .bodyToMono(Distance.class)
                 .block();

        return response;
    }

    public CityDetails getCityDetails(String cityWikiDataID) {
        CityDetails response= this.geoApiClient
                .get()
                .uri("/"+CITIES+"/"+cityWikiDataID)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CityDetails.class)
                .block();

        return response;
    }

}
