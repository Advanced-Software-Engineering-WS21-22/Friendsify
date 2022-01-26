package aau.at.friendsifyfrontendservice.inputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendsInput {

    private String email_p_initiator;

    private String email_p_friend;

    private boolean is_timed_out;

    public FriendsInput() {

    }

    public FriendsInput(String email_p_initiator, String email_p_friend, boolean is_timed_out) {
        this.email_p_initiator = email_p_initiator;
        this.email_p_friend = email_p_friend;
        this.is_timed_out = is_timed_out;
    }
}
