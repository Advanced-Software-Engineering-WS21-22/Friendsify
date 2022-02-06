package aau.at.friendsifyfriendsservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_friend;

    @Email
    @NotBlank(message = "Email_Initiator is mandatory!")
    private String email_p_initiator;

    @Email
    @NotBlank(message = "Email_friend is mandatory!")
    private String email_p_friend;

    private LocalDate fs_start_date;

    private boolean is_timed_out;

    public Friends() {
    }

    public Friends(Long id_friend, boolean is_timed_out, String email_p_initiator, String email_p_friend, LocalDate fs_start_date) {
        this.id_friend = id_friend;
        this.is_timed_out = is_timed_out;
        this.email_p_initiator = email_p_initiator;
        this.email_p_friend = email_p_friend;
        this.fs_start_date = fs_start_date;
    }

    public Long getId_friend() {
        return id_friend;
    }

    public boolean isIs_timed_out() {
        return is_timed_out;
    }

    public void setIs_timed_out(boolean timed_out) {
        this.is_timed_out = timed_out;
    }

    public String getEmail_p_initiator() {
        return email_p_initiator;
    }

    public void setEmail_p_initiator(String id_p_initiator) {
        this.email_p_initiator = id_p_initiator;
    }

    public String getEmail_p_friend() {
        return email_p_friend;
    }

    public void setEmail_p_friend(String id_p_friend) {
        this.email_p_friend = id_p_friend;
    }

    public LocalDate getFs_start_date() {
        return fs_start_date;
    }

    public void setFs_start_date(LocalDate fs_start_date) {
        this.fs_start_date = fs_start_date;
    }

    public void updateFromDto(Friends other) {
        this.setEmail_p_initiator(other.getEmail_p_initiator());
        this.setEmail_p_friend(other.getEmail_p_friend());
        this.setFs_start_date(other.getFs_start_date());
        this.setIs_timed_out(other.isIs_timed_out());
    }

    @Override
    public String toString() {
        return "Friends{" +
                "email_p_initiator='" + email_p_initiator + '\'' +
                ", email_p_friend='" + email_p_friend + '\'' +
                ", fs_start_date='" + fs_start_date + '\'' +
                ", is_timed_out='" + is_timed_out + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Friends){
            return this.email_p_initiator.equals(((Friends) obj).email_p_initiator) && this.email_p_friend.equals(((Friends) obj).email_p_friend) && this.is_timed_out == ((Friends) obj).is_timed_out && this.fs_start_date.equals(((Friends) obj).fs_start_date);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_friend, email_p_initiator, email_p_friend, fs_start_date, is_timed_out);
    }
}
