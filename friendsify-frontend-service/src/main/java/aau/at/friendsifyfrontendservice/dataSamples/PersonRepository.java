package aau.at.friendsifyfrontendservice.dataSamples;

import aau.at.friendsifyfrontendservice.inputs.PersonInput;
import aau.at.friendsifyfrontendservice.models.Person;

import java.time.LocalDate;
import java.util.ArrayList;

public class PersonRepository {

    private static PersonRepository personRepository = null;

    private ArrayList<Person> persons;

    private PersonRepository() {
        this.persons = new ArrayList<>();
        this.fillWithSamples();
    }

    public static PersonRepository getPersonRepository() {
        if(personRepository == null) {
            personRepository = new PersonRepository();
        }

        return personRepository;
    }

    private void fillWithSamples() {
        //PW1 = password
        Person p1 = new Person(Long.valueOf(0), "Max", "Mustermann", "max@mustermann.de", LocalDate.of(2000, 1,1), "Q483522", "Villach", "$2a$10$A7tRy7jyQsTKNVcEjkwlauKhSg6deU9bnpNJt2CeQQPkWJPKlhry6");

        //PW2 = house
        Person p2 = new Person(Long.valueOf(1), "Anna", "Mustermann", "anna@mustermann.de", LocalDate.of(2001, 1,1), "Q483522", "Villach", "$2a$10$v6vYLfGiwxC.Go1HyxKinuzNdhFto9ZW0K8cO6Y1LpNA5mt/YK5mK");

        //PW3 = animal
        Person p3 = new Person(Long.valueOf(2), "John", "Doe", "john.doe@email.com", LocalDate.of(1990, 6,6), "Q41753", "Klagenfurt", "$2a$10$suHpLlQe44a4IzfXSx6kUeX1VN6Os1thIBaLnr2OEkkJbeb.RVeVG");

        //PW4 = car
        Person p4 = new Person(Long.valueOf(3), "Hans", "Müller", "hans.m@gmail.com", LocalDate.of(1994, 8,18), "Q660687", "Velden am Wörthersee", "$2a$10$jyBKNyMulV6YVTgpl0M5EOl9Z2Iy/ncZvVdr.SAztZngb.qjpQJza");

        //PW5 = tree
        Person p5 = new Person(Long.valueOf(4), "Maria", "Schmidt", "m.schmidt@gmail.com", LocalDate.of(1994, 12,1), "Q875805", "Pörtschach am Wörthersee", "$2a$10$cQ2liy6Ut0AsaYNoQLBxOujRERYESHTRzBO0CVPuevrrMfpmuVHSi");

        //PW6 = computer
        Person p6 = new Person(Long.valueOf(5), "Lukas", "Maier", "lumai@gmx.com", LocalDate.of(1999, 10,15), "Q494604", "Sankt Veit an der Glan", "$2a$10$SzI/pP8kNZ4z.HcQeRWDp.wgFFpIqc7TKhDI2qTekvBKkmLMoLxIq");

        this.persons.add(p1);
        this.persons.add(p2);
        this.persons.add(p3);
        this.persons.add(p4);
        this.persons.add(p5);
        this.persons.add(p6);
    }

    public ArrayList<Person> getPersons() {
        return this.persons;
    }

    public Person findUser(String username) {
        Person searched = null;
        for (Person p: this.persons) {
            if(p.getEmail().equals(username)) {
                searched = p;
            }
        }
        return searched;
    }
}
