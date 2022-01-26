package aau.at.friendsifybirthdayservice;

import aau.at.friendsifybirthdayservice.exception.NoBirthdayException;
import aau.at.friendsifybirthdayservice.obj.Friend;
import aau.at.friendsifybirthdayservice.obj.Person;
import aau.at.friendsifybirthdayservice.service.BirthdayService;
import aau.at.friendsifybirthdayservice.service.EmailClient;
import aau.at.friendsifybirthdayservice.service.FriendClient;
import aau.at.friendsifybirthdayservice.service.PersonClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BirthdayServiceTest {

    @Mock
    private PersonClient personClient;

    @Mock
    private EmailClient emailClient;

    @Mock
    private FriendClient friendClient;

    @InjectMocks
    private BirthdayService birthdayService;

    private Person p1;
    private Person p2;
    private LocalDate now;
    private Person p3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        this.now = LocalDate.now();
        this.p1 = new Person();
        this.p1.setBirthday(this.now);
        this.p1.setEmail("test.mann1@email.com");
        this.p2 = new Person();
        this.p2.setEmail("test.mann2@email.com");
        this.p2.setBirthday(this.now.minusDays(1));

        this.p3 = new Person();
        this.p3.setBirthday(this.now.plusDays(10));
        this.p3.setEmail("test.mann3@email.com");
    }

    @Test
    void testFilterByBirthday() {

        List<Person> ps = Arrays.asList(p1, p2);

        List<Person> result = birthdayService.filterByBirthday(now, ps);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSendBirthdayReminders() {
        when(personClient.getPersons()).thenReturn(Collections.singletonList(p1));
        when(friendClient.getPersonsThatAreFriendOf(p1)).thenReturn(Collections.singletonList(new Friend()));
        when(personClient.getPersonsForFriendList(anyList())).thenReturn(Collections.singletonList(p2));
        when(emailClient.sendBirthdayReminder(anyString(), any())).thenReturn("send");

        birthdayService.sendBirthdayReminders();

        verify(personClient, times(1)).getPersons();
        verify(friendClient, times(1)).getPersonsThatAreFriendOf(p1);
        verify(personClient, times(1)).getPersonsForFriendList(anyList());
        verify(emailClient, times(1)).sendBirthdayReminder(anyString(), any());
    }

    @Test
    void testHappyBirthday() throws NoBirthdayException {
        when(personClient.getPerson(1L)).thenReturn(p1);
        when(personClient.getPerson(2L)).thenReturn(p2);
        when(emailClient.sendBirthdayWish(anyString(), anyString())).thenReturn("send");

        birthdayService.happyBirthday(2L, 1L);

        verify(personClient, times(2)).getPerson(anyLong());
        verify(emailClient, times(1)).sendBirthdayWish(anyString(), anyString());
    }

    @Test
    void testHappyBirthdayNotOnBirthday() {
        when(personClient.getPerson(1L)).thenReturn(p1);
        when(personClient.getPerson(3L)).thenReturn(p3);

        assertThrows(NoBirthdayException.class, () -> {
            birthdayService.happyBirthday(1L, 3L);
        });
    }

    @Test
    void testListBirthdayKids() {
        when(personClient.getPersons()).thenReturn(Arrays.asList(p1,p2,p3));

        List<Person> result = birthdayService.listBirthdayKids();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(personClient, times(1)).getPersons();
    }

    @Test
    void testlListBirthdayOfFriends() {
        when(personClient.getPerson(1L)).thenReturn(p1);
        when(friendClient.getFriendsOfPerson(p1)).thenReturn(Collections.singletonList(new Friend()));
        when(personClient.getPersonsForFriendList(anyList())).thenReturn(Collections.singletonList(p2));

        List<Person> result = birthdayService.listBirthdayOfFriends(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(personClient, times(1)).getPerson(anyLong());
        verify(friendClient, times(1)).getFriendsOfPerson(p1);
        verify(personClient, times(1)).getPersonsForFriendList(anyList());
    }

}
