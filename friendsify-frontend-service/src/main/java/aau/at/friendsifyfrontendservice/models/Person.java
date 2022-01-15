package aau.at.friendsifyfrontendservice.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class Person {

    private Long id_p;

    private String firstName;

    private String lastName;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String id_geoDB;

    private String city;

    private String password;

    private String repeat_password;

    private String password_hash;

    public Person() {

    }

    public Person(Long id_p, String firstName, String lastName, String email, LocalDate birthday, String id_geoDB, String city, String password, String repeat_password) {
        this.id_p = id_p;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.id_geoDB = id_geoDB;
        this.city = city;
        this.password = password;
        this.repeat_password = repeat_password;
    }

    public Person(Long id_p, String firstName, String lastName, String email, LocalDate birthday, String id_geoDB, String city, String password_hash) {
        this.id_p = id_p;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.id_geoDB = id_geoDB;
        this.city = city;
        this.password_hash = password_hash;
    }
}
