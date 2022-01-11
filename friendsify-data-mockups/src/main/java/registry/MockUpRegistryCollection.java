package registry;


import Entities.Friends;
import Entities.Person;

import java.time.LocalDate;

public class MockUpRegistryCollection {

    private static MockUpRegistryCollection mockUpRegistryCollection = null;

    private MockUpRegistry personRegistry;
    private MockUpRegistry friendsRegistry;

    private MockUpRegistryCollection() {
        this.personRegistry = new MockUpMockUpRegistryImpl<Person>();
        this.friendsRegistry = new MockUpMockUpRegistryImpl<Friends>();

        this.addPersonSamples();
        this.addFriendsSamples();
    }

    public static MockUpRegistryCollection getMockUpRegistryCollection() {
        if(mockUpRegistryCollection == null){
            mockUpRegistryCollection = new MockUpRegistryCollection();
        }

        return mockUpRegistryCollection;
    }

    public MockUpRegistry getPersonRegistry() {
        return personRegistry;
    }

    public MockUpRegistry getFriendsRegistry() {
        return friendsRegistry;
    }

    private void addPersonSamples() {
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

        this.personRegistry.addMockUp(p1);
        this.personRegistry.addMockUp(p2);
        this.personRegistry.addMockUp(p3);
        this.personRegistry.addMockUp(p4);
        this.personRegistry.addMockUp(p5);
        this.personRegistry.addMockUp(p6);
    }

    private void addFriendsSamples() {
        Friends fs1 = new Friends(Long.valueOf(0), Long.valueOf(3), Long.valueOf(4), LocalDate.of(2019,1,31), false);
        Friends fs2 = new Friends(Long.valueOf(1), Long.valueOf(1), Long.valueOf(0), LocalDate.of(2020,6,1), false);
        Friends fs3 = new Friends(Long.valueOf(2), Long.valueOf(6), Long.valueOf(5), LocalDate.of(2021,2,18), false);
        Friends fs4 = new Friends(Long.valueOf(3), Long.valueOf(4), Long.valueOf(5), LocalDate.of(2020,8,21), true);

        this.friendsRegistry.addMockUp(fs1);
        this.friendsRegistry.addMockUp(fs2);
        this.friendsRegistry.addMockUp(fs3);
        this.friendsRegistry.addMockUp(fs4);
    }
}
