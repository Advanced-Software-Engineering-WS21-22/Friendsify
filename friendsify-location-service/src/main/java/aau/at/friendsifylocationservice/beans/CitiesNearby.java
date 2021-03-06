package aau.at.friendsifylocationservice.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CitiesNearby {
    @JsonProperty("")
    private List<City> data;

    public CitiesNearby(List<City> data) {
        this.data = data;
    }
}
