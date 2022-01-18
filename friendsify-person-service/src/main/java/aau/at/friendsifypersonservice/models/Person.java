package aau.at.friendsifypersonservice.models;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_p;

    private String first_name;

    private String last_name;

    private LocalDate birthday;

    private String email;

    private String password;

    private String id_geoDB;

    private String city;

    public Person() {
    }

    public Person(Long id_p, String first_name, String last_name, LocalDate birthday, String email, String password, String id_geoDB, String city) {
        this.id_p = id_p;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.id_geoDB = id_geoDB;
        this.city = city;
    }

    public void update(Person updatedPerson){
        this.setFirst_name(updatedPerson.getFirst_name());
        this.setLast_name(updatedPerson.getLast_name());
        this.setEmail(updatedPerson.getEmail());
        this.setBirthday(updatedPerson.getBirthday());
        this.setCity(updatedPerson.getCity());
        this.setId_geoDB(updatedPerson.getId_geoDB());
        this.setPassword(updatedPerson.getPassword());
    }

    public Long getId_p() {
        return id_p;
    }

    public void setId_p(Long id) {
        this.id_p = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String firstName) {
        this.first_name = firstName;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String lastName) {
        this.last_name = lastName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String place) {
        this.city = place;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_geoDB() {
        return id_geoDB;
    }

    public void setId_geoDB(String id_geoDB) {
        this.id_geoDB = id_geoDB;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id_p +
                ", firstName='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", place='" + city + '\'' +
                '}';
    }
}
