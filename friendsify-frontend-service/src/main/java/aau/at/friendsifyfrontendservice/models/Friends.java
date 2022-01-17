package aau.at.friendsifyfrontendservice.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Friends {

    private Long id_fs;

    private Long id_initiator;

    private Long id_receiver;

    private LocalDate fs_start;

    private boolean is_timed_out;

    public Friends() {

    }

    public Friends(Long id_fs, Long id_initiator, Long id_receiver, LocalDate fs_start, boolean is_timed_out) {
        this.id_fs = id_fs;
        this.id_initiator = id_initiator;
        this.id_receiver = id_receiver;
        this.fs_start = fs_start;
        this.is_timed_out = is_timed_out;
    }
}
