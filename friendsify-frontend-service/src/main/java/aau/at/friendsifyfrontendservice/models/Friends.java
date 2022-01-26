package aau.at.friendsifyfrontendservice.models;

import aau.at.friendsifyfrontendservice.inputs.FriendsInput;
import aau.at.friendsifyfrontendservice.inputs.PersonInput;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
public class Friends {

    private Long id_friend;

    private String email_p_initiator;

    private String email_p_friend;

    private LocalDate fs_start_date;

    private boolean is_timed_out;

    public Friends() {

    }

    public Friends(Long id_friend, String email_p_initiator, String email_p_friend, LocalDate fs_start_date, boolean is_timed_out) {
        this.id_friend = id_friend;
        this.email_p_initiator = email_p_initiator;
        this.email_p_friend = email_p_friend;
        this.fs_start_date = fs_start_date;
        this.is_timed_out = is_timed_out;
    }

    public Friends(String email_p_initiator, String email_p_friend, LocalDate fs_start_date, boolean is_timed_out) {
        this.email_p_initiator = email_p_initiator;
        this.email_p_friend = email_p_friend;
        this.fs_start_date = fs_start_date;
        this.is_timed_out = is_timed_out;
    }

    public static Friends fromFriendsInput(FriendsInput friendsInput) {
        return new Friends(
                friendsInput.getEmail_p_initiator(),
                friendsInput.getEmail_p_friend(),
                LocalDate.now(),
                friendsInput.is_timed_out()
        );
    }
}
