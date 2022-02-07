package aau.at.anniversaryreminder;

import aau.at.anniversaryreminder.service.AnniversaryServiceImpl;
import aau.at.anniversaryreminder.service.FriendsService;
import aau.at.anniversaryreminder.service.PersonService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@ExtendWith(MockitoExtension.class)
public class AnniversaryServiceTest {

    @Mock
    private FriendsService friendsService;

    @Mock
    private PersonService personService;

    @InjectMocks
    private AnniversaryServiceImpl anniversaryService;

    private LocalDate date;
    private String emailInitiator;
    private String emailFriend;

    @BeforeEach
    public void setup() {
        this.date = LocalDate.parse("2000-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        this.emailInitiator = "initiator@mail";
        this.emailFriend = "friend@mail";
    }

    @AfterEach
    public void tearDown() {
        this.date = null;
        this.emailFriend = null;
        this.emailInitiator = null;
    }

    @Test
    public void testAnniversaryIsTodayCheck() {
        Mockito.when(friendsService.getFriendshipStartDate(emailInitiator, emailFriend)).thenReturn(LocalDate.parse("2000-01-01", DateTimeFormatter.ISO_LOCAL_DATE));
        if (!LocalDate.now().isEqual(date)) {
            // Today is not anniversary
            Assertions.assertFalse(anniversaryService.isTodayAnniversary(emailInitiator, emailFriend));
        } else {
            // Today is actually anniversary
            Assertions.assertTrue(anniversaryService.isTodayAnniversary(emailInitiator, emailFriend));
        }
        Mockito.verify(friendsService, Mockito.times(1)).getFriendshipStartDate(emailInitiator, emailFriend);
    }

    @Test
    public void testDaysLeftUntilAnniversary() {
        Mockito.when(friendsService.getFriendshipStartDate(emailInitiator, emailFriend)).thenReturn(LocalDate.parse("1990-01-31", DateTimeFormatter.ISO_LOCAL_DATE));
        Assertions.assertEquals(30, anniversaryService.daysUntilAnniversary(emailInitiator, emailFriend, date));
        Mockito.verify(friendsService, Mockito.times(1)).getFriendshipStartDate(emailInitiator, emailFriend);
    }

    @Test
    public void testDaysRemainingUntilNextAnniversary() {
        Mockito.when(friendsService.getFriendshipStartDate(emailInitiator, emailFriend)).thenReturn(LocalDate.parse("1990-01-31", DateTimeFormatter.ISO_LOCAL_DATE));
        Assertions.assertEquals(364, anniversaryService.daysUntilAnniversary(emailInitiator, emailFriend, LocalDate.parse("2001-02-01", DateTimeFormatter.ISO_LOCAL_DATE)));
        Mockito.verify(friendsService, Mockito.times(1)).getFriendshipStartDate(emailInitiator, emailFriend);
    }

    @Test
    public void testReminderAnswer() {
        Mockito.when(personService.getNameByEmail(emailInitiator)).thenReturn("Max Mustermann");
        Mockito.when(personService.getNameByEmail(emailFriend)).thenReturn("Bob Fisher");
        Mockito.when(friendsService.getFriendshipStartDate(emailInitiator, emailFriend)).thenReturn(date);

        int expectedYears = date.until(LocalDate.now()).getYears();
        String expectedAnswer = "Max Mustermann celebrates the " + expectedYears + " year anniversary of the friendship to Bob Fisher";
        Assertions.assertEquals(expectedAnswer, anniversaryService.getAnniversaryReminder(emailInitiator, emailFriend));
        Mockito.verify(personService, Mockito.times(1)).getNameByEmail(emailInitiator);
        Mockito.verify(personService, Mockito.times(1)).getNameByEmail(emailFriend);
        Mockito.verify(friendsService, Mockito.times(1)).getFriendshipStartDate(emailInitiator, emailFriend);
    }


}
