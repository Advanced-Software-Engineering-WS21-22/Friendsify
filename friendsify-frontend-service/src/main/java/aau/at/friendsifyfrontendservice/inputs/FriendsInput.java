package aau.at.friendsifyfrontendservice.inputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendsInput {

    private String email_p_initiator;

    private String email_p_friend;

    public FriendsInput() {

    }

    public FriendsInput(String email_p_initiator, String email_p_friend) {
        this.email_p_initiator = email_p_initiator;
        this.email_p_friend = email_p_friend;
    }
}
