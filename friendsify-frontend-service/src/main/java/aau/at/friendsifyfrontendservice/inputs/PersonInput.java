package aau.at.friendsifyfrontendservice.inputs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
@Getter
@Setter
public class PersonInput {

    private Long id_p;

    private String first_name;

    private String last_name;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String id_geoDB;

    private String city;

    private String password;

    private String repeat_password;

    private String password_hash;

    public PersonInput() {

    }

    public PersonInput(Long id_p, String firstName, String lastName, String email, LocalDate birthday, String id_geoDB, String city, String password, String repeat_password) {
        this.id_p = id_p;
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.birthday = birthday;
        this.id_geoDB = id_geoDB;
        this.city = city;
        this.password = password;
        this.repeat_password = repeat_password;
    }

    public PersonInput(Long id_p, String firstName, String lastName, String email, LocalDate birthday, String id_geoDB, String city, String password_hash) {
        this.id_p = id_p;
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.birthday = birthday;
        this.id_geoDB = id_geoDB;
        this.city = city;
        this.password_hash = password_hash;
    }

    public String getPassword_hash() {
        if(this.password_hash != null) {
            return this.password_hash;
        } else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return passwordEncoder.encode(this.password);
        }
    }



}
