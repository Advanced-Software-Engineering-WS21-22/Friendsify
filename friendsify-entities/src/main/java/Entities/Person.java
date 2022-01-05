package Entities;

import java.util.Date;

public class Person {

    private int id_p;
    private String name;
    private String email;
    private Date birthday;
    private String id_geoDB;

    public Person(int id_p, String name, String email, Date birthday, String id_geoDB) {
        this.id_p =id_p;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.id_geoDB = id_geoDB;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getId_geoDB() {
        return id_geoDB;
    }

    public void setId_geoDB(String id_geoDB) {
        this.id_geoDB = id_geoDB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
