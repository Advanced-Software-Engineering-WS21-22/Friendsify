package aau.at.friendsifybirthdayservice.obj;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
}
