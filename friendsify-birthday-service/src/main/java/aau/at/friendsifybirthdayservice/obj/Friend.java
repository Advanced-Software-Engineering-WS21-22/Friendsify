package aau.at.friendsifybirthdayservice.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    @JsonProperty("email_p_initiator")
    private String emailInitiator;

    @JsonProperty("email_p_friend")
    private String emailFriend;
}