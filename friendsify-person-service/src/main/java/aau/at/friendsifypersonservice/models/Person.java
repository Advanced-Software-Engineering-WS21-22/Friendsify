package aau.at.friendsifypersonservice.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_p;

    private String first_name;

    private String last_name;

    private LocalDate birthday;

    @Email
    @NotBlank(message = "Email is mandatory!")
    @Column(unique = true)
    private String email;

    private String password;

    private String id_geoDB;

    private String city;

    public Person() {
    }

    public Person(String first_name, String last_name, LocalDate birthday, String email, String password, String id_geoDB, String city) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.id_geoDB = id_geoDB;
        this.city = city;
    }

    public Person(Long id, String first_name, String last_name, LocalDate birthday, String email, String password, String id_geoDB, String city) {
        this.id_p=id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.id_geoDB = id_geoDB;
        this.city = city;
    }

    public void update(Person updatedPerson) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            if ((this.id_p != null && ((Person) obj).getId_p() != null && this.id_p.equals(((Person) obj).getId_p())
                    || this.id_p == ((Person) obj).getId_p()) &&
                    ((this.first_name != null && ((Person) obj).getFirst_name() != null &&
                            this.first_name.equals(((Person) obj).getFirst_name())) ||
                            this.first_name == ((Person) obj).getFirst_name()
                    ) &&
                    ((this.last_name != null && ((Person) obj).getLast_name() != null &&
                            this.last_name.equals(((Person) obj).getLast_name())) ||
                            this.last_name == (((Person) obj).getLast_name())
                    ) &&
                    (this.email != null && ((Person) obj).getEmail() != null &&
                            this.email.equals(((Person) obj).getEmail())
                    ) &&
                    ((this.birthday != null && ((Person) obj).getBirthday() != null &&
                            this.birthday.equals(((Person) obj).getBirthday())) ||
                            this.birthday == (((Person) obj).getBirthday())
                    ) &&
                    ((this.city != null && ((Person) obj).getCity() != null &&
                            this.city.equals(((Person) obj).getCity())) ||
                            this.city == (((Person) obj).getCity())
                    ) &&
                    ((this.id_geoDB != null && ((Person) obj).getId_geoDB() != null &&
                            this.id_geoDB.equals(((Person) obj).getId_geoDB())) ||
                            this.id_geoDB == (((Person) obj).getId_geoDB())
                    ) &&
                    ((this.password != null && ((Person) obj).getPassword() != null &&
                            this.password.equals(((Person) obj).getPassword())) ||
                            this.password == (((Person) obj).getPassword()))
            ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_p, first_name, last_name, birthday, email, password, id_geoDB, city);
    }
}
