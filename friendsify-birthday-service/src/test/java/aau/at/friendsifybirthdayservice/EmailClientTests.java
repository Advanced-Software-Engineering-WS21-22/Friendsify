package aau.at.friendsifybirthdayservice;

import aau.at.friendsifybirthdayservice.obj.Email;
import aau.at.friendsifybirthdayservice.obj.Person;
import aau.at.friendsifybirthdayservice.service.EmailClient;
import aau.at.friendsifybirthdayservice.constants.EmailConstants;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

;

class EmailClientTests {

    private static MockWebServer backend;
    private EmailClient emailClient;

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
        this.emailClient = new EmailClient(url);
    }

    @Test
    void testSendMail() {
        Email email = new Email("from", "to", "subject", "text");
        backend.enqueue(new MockResponse().setBody(email.toString()).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        String result = emailClient.send(email);

        assertNotNull(result);
        assertEquals(result, email.toString());
    }

    @Test
    void testSendBirthdayWish() {
        String em ="test.mann@email.com";
        Email email = new Email(em, em, EmailConstants.BIRTHDAY_SUBJECT, EmailConstants.BIRTHDAY_WISH);
        backend.enqueue(new MockResponse().setBody(email.toString()).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        String result = emailClient.sendBirthdayWish(em, em);

        assertNotNull(result);
        assertEquals(result, email.toString());
    }

    @Test
    void testSendReminder() {
        Person bk = new Person();
        bk.setFirstName("Test");
        bk.setLastName("Mann");
        bk.setEmail("test.mann@email.com");
        Email email = new Email(EmailConstants.DEFAULT_FROM, bk.getEmail(), EmailConstants.BIRTHDAY_REMINDER, String.format(EmailConstants.BIRTHDAY_REMINDER_TEXT, bk.getFullName()));
        backend.enqueue(new MockResponse().setBody(email.toString()).setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        String result = emailClient.sendBirthdayReminder(bk.getEmail(), bk);

        assertNotNull(result);
        assertEquals(result, email.toString());
    }

}