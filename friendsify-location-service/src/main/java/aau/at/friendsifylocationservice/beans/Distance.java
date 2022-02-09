package aau.at.friendsifylocationservice.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Distance {
    @JsonProperty("data")
    private int distanceInKM;
}
