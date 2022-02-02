package aau.at.friendsifyrecommendationsservice;

import aau.at.friendsifyrecommendationsservice.models.Friends;
import aau.at.friendsifyrecommendationsservice.models.Person;

import java.time.LocalDate;

public class TestSamples {

    public static Person[] getPersonSamples() {
        Person[] samples = new Person[5];
        samples[0] = new Person(1L, "Max", "Mustermann", "max@mustermann.de", LocalDate.of(1999, 01, 22), "Q1234", "Klagenfurt", "password");
        samples[1] = new Person(2L, "John", "Doe", "john@doe.de", LocalDate.of(1995, 03, 12), "Q1234", "Klagenfurt", "password");
        samples[2] = new Person(3L, "Anna", "Mustermann", "anna@mustermann.de", LocalDate.of(2000, 8, 14), "Q1234", "Klagenfurt", "password");
        samples[3] = new Person(4L, "Hans", "Lustig", "hansi@lustig.de", LocalDate.of(1999, 01, 23), "Q1234", "Klagenfurt", "password");
        samples[4] = new Person(5L, "Bob", "Ross", "bob@ross.de", LocalDate.of(1990, 01, 22), "Q1234", "Klagenfurt", "password");

        return samples;
    }

    public static Friends[] getFriendsSamples() {
        Friends[] samples = new Friends[7];
        //Max Mustermann friends
        samples[0] = new Friends(1L, "max@mustermann.de", "john@doe.de", LocalDate.of(2021, 1,1), false);
        samples[1] = new Friends(2L, "anna@mustermann.de", "max@mustermann.de", LocalDate.of(2021, 1,1), false);
        samples[2] = new Friends(3L, "max@mustermann.de", "bob@ross.de", LocalDate.of(2021, 1,1), false);
        samples[3] = new Friends(4L, "max@mustermann.de", "hansi@lustig.de", LocalDate.of(2021, 1,1), false);


        //Friends of Max Mustermanns friends (Anna has most common -> 2)
        samples[4] = new Friends(5L, "bob@ross.de", "hansi@lustig.de", LocalDate.of(2021, 1,1), false);
        samples[5] = new Friends(7L, "anna@mustermann.de", "john@doe.de", LocalDate.of(2021, 1,1), false);
        samples[6] = new Friends(8L, "hansi@lustig.de", "anna@mustermann.de", LocalDate.of(2021, 1,1), false);
        return samples;
    }
}
