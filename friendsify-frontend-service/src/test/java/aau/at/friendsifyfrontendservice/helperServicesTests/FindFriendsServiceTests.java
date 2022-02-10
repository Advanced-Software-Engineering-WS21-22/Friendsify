package aau.at.friendsifyfrontendservice.helperServicesTests;

import aau.at.friendsifyfrontendservice.models.Friends;
import aau.at.friendsifyfrontendservice.models.Person;
import aau.at.friendsifyfrontendservice.services.FindFriendsService;
import aau.at.friendsifyfrontendservice.services.FriendsService;
import aau.at.friendsifyfrontendservice.services.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class FindFriendsServiceTests {

    @Mock
    private PersonService personServiceMock;

    @Mock
    private FriendsService friendsService;

    @InjectMocks
    private FindFriendsService findFriendsService;

    private Person[] allPersons;

    private Friends[] friends;

    @Test
    public void testFindSelectables() {
        Mockito.when(this.personServiceMock.getPersons()).thenReturn(this.getPersonSamples());
        Mockito.when(this.friendsService.getFriendsByInitiator("max@mustermann.de")).thenReturn(this.getFriendsSamples());

        Person[] expected = new Person[1];
        expected[0] = this.getPersonSamples()[2];

        this.findFriendsService.loadData("max@mustermann.de");
        Person[] actual = this.findFriendsService.findSelectablePersons("max@mustermann.de");

        Assert.assertEquals(expected[0].getId_p(), actual[0].getId_p());
    }

    private Person[] getPersonSamples() {
        Person[] samples = new Person[3];
        samples[0] = new Person(1L, "Max", "Mustermann", "max@mustermann.de", LocalDate.of(1999, 01, 22), "Q1234", "Klagenfurt", "password");
        samples[1] = new Person(2L, "John", "Doe", "john@doe.de", LocalDate.of(1995, 03, 12), "Q1234", "Klagenfurt", "password");
        samples[2] = new Person(3L, "Anna", "Mustermann", "anna@mustermann.de", LocalDate.of(2000, 8, 14), "Q1234", "Klagenfurt", "password");

        return samples;
    }

    private Friends[] getFriendsSamples() {
        Friends[] samples = new Friends[1];
        samples[0] = new Friends(1L, "max@mustermann.de", "john@doe.de", LocalDate.of(2021, 1,1), false);

        return samples;
    }

}
