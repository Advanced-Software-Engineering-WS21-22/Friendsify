package Entities;

import java.time.LocalDate;

public class Person {

    private Long id_p;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthday;

    private String id_geoDB;

    private String city;

    private String password_hash;

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

    public Person(){}

    public Long getId_p() {
        return id_p;
    }

    public void setId_p(Long id_p) {
        this.id_p = id_p;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getId_geoDB() {
        return id_geoDB;
    }

    public void setId_geoDB(String id_geoDB) {
        this.id_geoDB = id_geoDB;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
}
