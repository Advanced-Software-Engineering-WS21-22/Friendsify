package Entities;

import java.util.Date;

public class Person {

    private int id_p;
    private String name;
    private Date birthday;
    private int id_geoDB;

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

    public int getId_geoDB() {
        return id_geoDB;
    }

    public void setId_geoDB(int id_geoDB) {
        this.id_geoDB = id_geoDB;
    }

    public Person(int id_p, String name, Date birthday, int id_geoDB) {
        this.id_p =id_p;
        this.name = name;
        this.birthday = birthday;
        this.id_geoDB = id_geoDB;
    }
}
