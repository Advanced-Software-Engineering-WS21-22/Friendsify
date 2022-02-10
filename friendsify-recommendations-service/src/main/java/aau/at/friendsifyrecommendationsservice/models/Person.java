package aau.at.friendsifyrecommendationsservice.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Person {

    private Long id_p;

    private String first_name;

    private String last_name;

    private String email;

    private LocalDate birthday;

    private String id_geoDB;

    private String city;

    private String password;

    public Person() {

    }

    public Person(Long id_p, String first_name, String last_name, String email, LocalDate birthday, String id_geoDB, String city, String password) {
        this.id_p = id_p;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.birthday = birthday;
        this.id_geoDB = id_geoDB;
        this.city = city;
        this.password = password;
    }
}
