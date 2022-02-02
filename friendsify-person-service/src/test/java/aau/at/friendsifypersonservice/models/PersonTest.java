package aau.at.friendsifypersonservice.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person p1;
    Person p1_2;
    Person p2;

    @BeforeEach
    void setUp(){
        p1 = new Person(1L,"Max", "Mustermann", LocalDate.of(2000,1,1), "max@mustermann.de", "cGFzc3dvcmQ=", "Q483522", "Villach");
        p1_2 = new Person(1L,"Max", "Mustermann", LocalDate.of(2000,1,1), "max@mustermann.de", "cGFzc3dvcmQ=", "Q483522", "Villach");
        p2 = new Person(2L,"Mini", "Maustermann", LocalDate.of(2000,1,2), "mini@maustermann.de", "cGFzc3dvcmQ", "Q48352", "Klagenfurt");
    }

    @AfterEach
    void tearDown(){
        p1=null;
        p1_2=null;
        p2=null;
    }

    @Test
    void testEqualsTrue() {
        assertEquals(p1_2,p1);
    }

    @Test
    void testEqualsTrueWithNullCity() {
        p1.setCity(null);
        p1_2.setCity(null);
        assertEquals(p1_2,p1);
    }

    @Test
    void testEqualsFalse() {
        assertNotEquals(p2,p1);
    }

    @Test
    void update() {
        p1.update(p2);
        Person expected= new Person(1L,"Mini", "Maustermann", LocalDate.of(2000,1,2), "mini@maustermann.de", "cGFzc3dvcmQ", "Q48352", "Klagenfurt");

        assertEquals(expected,p1);
    }

}