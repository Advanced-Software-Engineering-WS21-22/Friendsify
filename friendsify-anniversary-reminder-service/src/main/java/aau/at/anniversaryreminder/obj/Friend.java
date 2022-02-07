package aau.at.anniversaryreminder.obj;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Friend {

    @JsonProperty("email_p_initiator")
    private String emailInitiator;

    @JsonProperty("email_p_friend")
    private String emailFriend;

    @JsonProperty("fs_start_date")
    private LocalDate friendshipStartDate;

    @JsonProperty("is_timed_out")
    private boolean isTimedOut;
}