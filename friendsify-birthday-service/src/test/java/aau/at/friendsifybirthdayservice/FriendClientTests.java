package aau.at.friendsifybirthdayservice;

import aau.at.friendsifybirthdayservice.exception.ResourceNotFoundException;
import aau.at.friendsifybirthdayservice.obj.Friend;
import aau.at.friendsifybirthdayservice.obj.Person;
import aau.at.friendsifybirthdayservice.service.FriendClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FriendClientTests {

    private static MockWebServer backend;
    private FriendClient friendClient;

    @BeforeAll
    public static void startUp() throws IOException {
        backend = new MockWebServer();
        backend.start();
    }

    @AfterAll
    public static void shutdown() throws IOException {
        backend.shutdown();
    }

    @BeforeEach
    public void setUp() throws IOException {
        String url = "http://localhost:" + backend.getPort();
        this.friendClient = new FriendClient(url);
    }

    @Test
    void testFriendsOfPerson() {

        String email = "test.mann@email.com";
        Person p = new Person();
        p.setEmail(email);
        String response = "[{\"email_p_initiator\":\""+email+"\",\"email_p_friend\":\"email\"},{\"email_p_initiator\":\""+email+"\",\"email_p_friend\":\"email\"}]";
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        List<Friend> fl = friendClient.getFriendsOfPerson(p);

        assertNotNull(fl);
        assertEquals(2, fl.size());
        fl.forEach(f -> assertEquals(email, f.getEmailInitiator()));
    }

    @Test
    void testPersonsThatAreFriendOf() {

        String email = "test.mann@email.com";
        Person p = new Person();
        p.setEmail(email);
        String response = "[{\"email_p_initiator\":\"email\",\"email_p_friend\":\""+email+"\"},{\"email_p_initiator\":\"email\",\"email_p_friend\":\""+email+"\"}]";
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        List<Friend> fl = friendClient.getPersonsThatAreFriendOf(p);

        assertNotNull(fl);
        assertEquals(2, fl.size());
        fl.forEach(f -> assertEquals(email, f.getEmailFriend()));
    }

    @Test
    void testPersonInValid() {
        backend.enqueue(new MockResponse().setResponseCode(404).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        assertThrows(ResourceNotFoundException.class, () -> {
            friendClient.getFriendsOfPerson(new Person());
        });

    }
}