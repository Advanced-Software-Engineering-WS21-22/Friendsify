package aau.at.friendsifyjokeservice;

import aau.at.friendsifyjokeservice.exception.PersonNotFoundException;
import aau.at.friendsifyjokeservice.services.PersonClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PersonClientTests {

    private static MockWebServer backend;
    private PersonClient personClient;

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
    }

    @Test
    public void testPersonValid() {
        String email = "test.mann@email.com";
        String response
                = "{\"id_p\":4,\"first_name\":\"Test\",\"last_name\":\"Mann\",\"birthday\":\"2002-01-26\",\"email\":\""+email+"\",\"password\":\"cGFzc3dvcmQ=\",\"id_geoDB\":\"Q41753\",\"city\":\"Klagenfurt\"}";
        backend.enqueue(new MockResponse().setBody(response).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        String emailOfPerson = personClient.emailOfPerson(4l);

        assertNotNull(emailOfPerson);
        assertEquals(email, emailOfPerson);
    }

    @Test
    public void testPersonInValid() {
        backend.enqueue(new MockResponse().setResponseCode(404).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        assertThrows(PersonNotFoundException.class, () -> {
            personClient.emailOfPerson(1l);
        });

    }

}
