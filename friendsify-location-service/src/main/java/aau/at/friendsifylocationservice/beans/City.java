package aau.at.friendsifylocationservice.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City {
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
