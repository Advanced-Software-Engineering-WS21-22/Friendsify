package aau.at.friendsifyfrontendservice.dataTransferObjects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

public class PersonDTO {

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthday;

    private String id_geoDB;

    private String city;

    private String password_hash;


    public PersonDTO(String firstName, String lastName, String email, LocalDate birthday, String id_geoDB, String city, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.id_geoDB = id_geoDB;
        this.city = city;
        this.password_hash = this.encode_password(password);
    }

    private String encode_password(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
