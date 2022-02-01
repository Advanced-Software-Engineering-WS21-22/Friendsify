package aau.at.friendsifybirthdayservice;

import aau.at.friendsifybirthdayservice.exception.ResourceNotFoundException;
import aau.at.friendsifybirthdayservice.obj.Friend;
import aau.at.friendsifybirthdayservice.obj.Person;
import aau.at.friendsifybirthdayservice.service.PersonClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonClientTests {

    private static MockWebServer backend;
    private PersonClient personClient;
    private String email;
    private String response;

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
        this.personClient = new PersonClient(url);
        this.email = "test.mann@email.com";
        this.response = "{\"id_p\":4,\"first_name\":\"Test\",\"last_name\":\"Mann\",\"birthday\":\"2002-01-26\",\"email\":\""+email+"\",\"password\":\"cGFzc3dvcmQ=\",\"id_geoDB\":\"Q41753\",\"city\":\"Klagenfurt\"}";

    }

    @Test
    void testPersonByIdValid() {
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        Person p = personClient.getPerson(4L);

        assertNotNull(p);
        assertEquals(email, p.getEmail());
    }
    @Test
    void testPersonByEmailValid() {
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        Person p = personClient.getPerson(email);

        assertNotNull(p);
        assertEquals(email, p.getEmail());
    }


    @Test
    void testPersonInValid() {
        backend.enqueue(new MockResponse().setResponseCode(404).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        assertThrows(ResourceNotFoundException.class, () -> {
            personClient.getPerson(1L);
        });
    }

    @Test
    void testGetPersons() {
        response = "[{\"id_p\":0,\"first_name\":\"Max\",\"last_name\":\"Mustermann\",\"birthday\":\"2000-01-01\",\"email\":\"max@mustermann.de\",\"password\":\"cGFzc3dvcmQ= \",\"id_geoDB\":\"Q483522\",\"city\":\"Villach\"},{\"id_p\":1,\"first_name\":\"Anna\",\"last_name\":\"Mustermann\",\"birthday\":\"2001-01-01\",\"email\":\"anna@mustermann.de\",\"password\":\"cGFzc3dvcmQ=\",\"id_geoDB\":\"Q483522\",\"city\":\"Villach\"}]";
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        List<Person> pl = personClient.getPersons();

        assertNotNull(pl);
        assertEquals(2, pl.size());
    }

    @Test
    void testFriendsListToPersons() {
        List<Friend> fl = new ArrayList<>();
        fl.add(new Friend(email, email));
        fl.add(new Friend(email, email));
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        List<Person> persons = personClient.getPersonsForFriendList(fl);

        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

}