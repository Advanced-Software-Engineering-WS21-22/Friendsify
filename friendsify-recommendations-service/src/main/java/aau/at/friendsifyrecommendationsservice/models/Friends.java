package aau.at.friendsifyrecommendationsservice.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
}
