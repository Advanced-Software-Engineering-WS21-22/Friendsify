package aau.at.friendsifylocationservice.beans;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDetails {
    @JsonProperty("")
    private City data;

    public CityDetails(City data) {
        this.data = data;
    }
}
