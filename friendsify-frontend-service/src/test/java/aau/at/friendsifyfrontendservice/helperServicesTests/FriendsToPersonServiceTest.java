package aau.at.friendsifyfrontendservice.helperServicesTests;

import aau.at.friendsifyfrontendservice.models.Person;
import aau.at.friendsifyfrontendservice.services.FriendsToPersonService;
import aau.at.friendsifyfrontendservice.services.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FriendsToPersonServiceTest {

    @Mock
    private PersonService personServiceMock;

    @InjectMocks
    private FriendsToPersonService friendsToPersonService;

    @Test
    public void testGetPersonByEmail() {
        Person[] allPersons = new Person[1];
        allPersons[0] =  new Person(0L, "Max", "Mustermann", "max@mustermann.de", LocalDate.now(), "Q1234", "Klagenfurt", "password_hash");
        
        when(this.personServiceMock.getPersons()).thenReturn(allPersons);
        
        this.friendsToPersonService.loadPersons();

        Assert.assertEquals(allPersons[0], this.friendsToPersonService.getPerson("max@mustermann.de"));
        Assert.assertEquals(null, this.friendsToPersonService.getPerson("noperson@list.com"));
    }

}
