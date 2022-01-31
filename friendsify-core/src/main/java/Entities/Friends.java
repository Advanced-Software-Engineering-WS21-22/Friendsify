package Entities;

import java.time.LocalDate;

public class Friends {

    private Long id_fs;

    private Long id_p_initiator;

    private Long id_p_friend;

    private LocalDate fs_start_date;

    private boolean is_timed_out;


    public Friends(Long id_fs, Long id_p_initiator, Long id_p_friend, LocalDate fs_start_date, boolean is_timed_out) {
        this.id_fs = id_fs;
        this.id_p_initiator = id_p_initiator;
        this.id_p_friend = id_p_friend;
        this.fs_start_date = fs_start_date;
        this.is_timed_out = is_timed_out;
    }

    public Friends(){}

    public Long getId_fs() {
        return id_fs;
    }

    public void setId_fs(Long id_fs) {
        this.id_fs = id_fs;
    }

    public Long getId_p_initiator() {
        return id_p_initiator;
    }

    public void setId_p_initiator(Long id_p_initiator) {
        this.id_p_initiator = id_p_initiator;
    }

    public Long getId_p_friend() {
        return id_p_friend;
    }

    public void setId_p_friend(Long id_p_friend) {
        this.id_p_friend = id_p_friend;
    }

    public LocalDate getFs_start_date() {
        return fs_start_date;
    }

    public void setFs_start_date(LocalDate fs_start_date) {
        this.fs_start_date = fs_start_date;
    }

    public Boolean getIs_timed_out() {
        return is_timed_out;
    }

    public void setIs_timed_out(Boolean is_timed_out) {
        this.is_timed_out = is_timed_out;
    }

}
