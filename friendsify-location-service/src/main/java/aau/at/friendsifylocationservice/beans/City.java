package aau.at.friendsifylocationservice.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City {
    public City(int id, String wikiDataId, String type, String city, String name, String country, String countryCode, String region, String regionCode, int elevationMeters, double latitude, double longitude, long population, String timezone, boolean deleted) {
        this.id = id;
        this.wikiDataId = wikiDataId;
        this.type = type;
        this.city = city;
        this.name = name;
        this.country = country;
        this.countryCode = countryCode;
        this.region = region;
        this.regionCode = regionCode;
        this.elevationMeters = elevationMeters;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
        this.timezone = timezone;
        this.deleted = deleted;
    }

    @JsonProperty("")
    private int id;
    @JsonProperty("")
    private String wikiDataId;
    @JsonProperty("")
    private String type;
    @JsonProperty("")
    private String city;
    @JsonProperty("")
    private String name;
    @JsonProperty("")
    private String country;
    @JsonProperty("")
    private String countryCode;
    @JsonProperty("")
    private String region;
    @JsonProperty("")
    private String regionCode;
    @JsonProperty("")
    private int elevationMeters;
    @JsonProperty("")
    private double latitude;
    @JsonProperty("")
    private double longitude;
    @JsonProperty("")
    private long population;
    @JsonProperty("")
    private String timezone;
    @JsonProperty("")
    private boolean deleted;
}
