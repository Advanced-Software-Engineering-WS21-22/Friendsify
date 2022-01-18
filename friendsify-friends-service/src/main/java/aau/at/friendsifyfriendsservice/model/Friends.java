package aau.at.friendsifyfriendsservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    boolean timed_out;

    int id_p_initiator;

    int id_p_friend;

    Date fs_start_date;

    public Friends() {
    }

    public Friends(Long id, boolean timed_out, int id_p_initiator, int id_p_friend, Date fs_start_date) {
        this.id = id;
        this.timed_out = timed_out;
        this.id_p_initiator = id_p_initiator;
        this.id_p_friend = id_p_friend;
        this.fs_start_date = fs_start_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isTimed_out() {
        return timed_out;
    }

    public void setTimed_out(boolean timed_out) {
        this.timed_out = timed_out;
    }

    public int getId_p_initiator() {
        return id_p_initiator;
    }

    public void setId_p_initiator(int id_p_initiator) {
        this.id_p_initiator = id_p_initiator;
    }

    public int getId_p_friend() {
        return id_p_friend;
    }

    public void setId_p_friend(int id_p_friend) {
        this.id_p_friend = id_p_friend;
    }

    public Date getFs_start_date() {
        return fs_start_date;
    }

    public void setFs_start_date(Date fs_start_date) {
        this.fs_start_date = fs_start_date;
    }

    public void updateFromDto(Friends other) {
        this.setId_p_initiator(other.getId_p_initiator());
        this.setId_p_friend(other.getId_p_friend());
        this.setFs_start_date(other.getFs_start_date());
        this.setTimed_out(other.isTimed_out());
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id_p_initiator='" + id_p_initiator + '\'' +
                ", id_p_friend='" + id_p_friend + '\'' +
                ", fs_start_date='" + fs_start_date + '\'' +
                ", timed_out='" + timed_out + '\'' +
                '}';
    }
}
