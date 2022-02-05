package aau.at.anniversaryreminder;

import aau.at.anniversaryreminder.obj.Friend;
import aau.at.anniversaryreminder.service.FriendsServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
public class FriendsServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FriendsServiceImpl friendsService;

    private Friend[] friends;
    private String emailInitiator;
    private String emailFriend;
    private LocalDate friendshipStart;
    private String host;

    @BeforeEach
    public void setup() {
        this.host = "sampleHost";
        this.emailFriend = "friend@mail";
        this.emailInitiator = "initiator@mail";
        this.friendshipStart = LocalDate.parse("2000-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        this.friendsService = new FriendsServiceImpl(host, restTemplate);
        this.friends = new Friend[2];
        this.friends[0] = new Friend(emailInitiator, emailFriend, friendshipStart, false);
        this.friends[1] = new Friend(emailInitiator, emailInitiator, friendshipStart, true);
    }

    @AfterEach
    public void tearDown() {
        this.host = null;
        this.friendsService = null;
        this.friends = null;
        this.emailInitiator = null;
        this.emailFriend = null;
        this.friendshipStart = null;
    }

    @Test
    public void testGetFriends() {
        String filter = "?email_initiator={email}";
        Mockito.when(restTemplate.getForObject(host + filter, Friend[].class, emailInitiator)).thenReturn(this.friends);
        Assertions.assertArrayEquals(friends, friendsService.getFriendsOf(emailInitiator).toArray());
        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(host + filter, Friend[].class, emailInitiator);
    }

    @Test
    public void testAreTheyFriends() {
        String filter = "?email_initiator={email}";
        Mockito.when(restTemplate.getForObject(host + filter, Friend[].class, emailInitiator)).thenReturn(this.friends);
        Assertions.assertTrue(friendsService.areTheyFriends(emailInitiator, emailFriend));
        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(host + filter, Friend[].class, emailInitiator);
    }

    @Test
    public void testFriendShipStartDate() {
        String filter = "?email_initiator={emailInitiator}&email_friend={emailFriend}";

        Friend[] expectedList = new Friend[1];
        expectedList[0] = new Friend(emailInitiator, emailFriend, friendshipStart, false);

        Mockito.when(restTemplate.getForObject(host + filter, Friend[].class, emailInitiator, emailFriend)).thenReturn(expectedList);
        Assertions.assertEquals(friendshipStart, friendsService.getFriendshipStartDate(emailInitiator, emailFriend));
        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(host + filter, Friend[].class, emailInitiator, emailFriend);
    }
}
