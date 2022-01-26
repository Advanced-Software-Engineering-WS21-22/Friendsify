package aau.at.friendsifyjokeservice;

import aau.at.friendsifyjokeservice.enums.JokeTypes;
import aau.at.friendsifyjokeservice.obj.Email;
import aau.at.friendsifyjokeservice.services.EmailClient;
import aau.at.friendsifyjokeservice.services.JokeClient;
import aau.at.friendsifyjokeservice.services.PersonClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JokeClientMockTests {

    @Mock
    PersonClient personClient;

    @Mock
    EmailClient emailClient;

    @Spy
    @InjectMocks
    JokeClient jokeClient;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTellYourFriendAJoke() {
        Long personId = 1l;
        Long friendId = 2l;
        when(personClient.emailOfPerson(personId)).thenReturn("person@email.com");
        when(personClient.emailOfPerson(friendId)).thenReturn("friend@email.com");
        final String email = "email";
        when(emailClient.send(any(Email.class))).thenReturn(email);
        doReturn("joke").when(jokeClient).randomJoke();
        doCallRealMethod().when(jokeClient).tellYourFriendAJoke(anyLong(), anyLong());

        String result = jokeClient.tellYourFriendAJoke(personId, friendId);

        assertNotNull(result);
        assertEquals(email, result);
        verify(personClient, times(1)).emailOfPerson(personId);
        verify(personClient, times(1)).emailOfPerson(friendId);
        verify(emailClient, times(1)).send(any(Email.class));
        verify(jokeClient, times(1)).randomJoke();
    }

    @Test
    public void testGetJokeByType() {
        String joke = "joke";
        String jokeRandom = joke + "Random";
        String jokeJod = joke + "Jod";
        doReturn(jokeJod).when(jokeClient).jokeOfTheDay();
        doReturn(jokeRandom).when(jokeClient).randomJoke();

        String resultJokeDefault = jokeClient.getJokebyType("default");
        String resultJokeRandom = jokeClient.getJokebyType("random");
        String resultJokeJod = jokeClient.getJokebyType("jod");

        assertEquals(jokeRandom, resultJokeDefault);
        assertEquals(jokeRandom, resultJokeRandom);
        assertEquals(jokeJod, resultJokeJod);
        verify(jokeClient, times(2)).randomJoke();
        verify(jokeClient, times(1)).jokeOfTheDay();
    }

    @Test
    public void testJokeTypeDetermination() {
        assertEquals(JokeTypes.RANDOM, JokeTypes.find("default"));
        assertEquals(JokeTypes.RANDOM, JokeTypes.find("random"));
        assertEquals(JokeTypes.JOD, JokeTypes.find("jod"));
    }
}
