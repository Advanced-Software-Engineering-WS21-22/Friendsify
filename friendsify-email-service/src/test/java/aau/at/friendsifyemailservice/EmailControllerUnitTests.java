package aau.at.friendsifyemailservice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static aau.at.friendsifyemailservice.ConfigurationProperties.*;

public class EmailControllerUnitTests {

    private EmailController emailController;
    private EmailService emailServiceMock;
    private Email email;

    @BeforeEach
    public void init() {
        this.emailServiceMock = Mockito.mock(EmailService.class);
        this.emailController = new EmailController(emailServiceMock);
        this.email = new Email(SAMPLE_SENDER_GMAIL, SAMPLE_RECEIVER_GMAIL, SAMPLE_SUBJECT, SAMPLE_TEXT);
    }

    @AfterEach
    public void tearDown() {
        this.email = null;
        this.emailController = null;
        this.emailServiceMock = null;
    }

    @Test
    public void testEMailControllerGetRequest() {
        String result = this.emailController.showEmailsPage();
        Assertions.assertEquals("emails", result);
    }

    @Test
    public void testEMailControllerPostRequest() {
        String actualReturn = this.emailController.createMail(this.email);
        String expectedReturn = this.email.toString();
        Mockito.verify(this.emailServiceMock, Mockito.times(1)).sendEmail(SAMPLE_RECEIVER_GMAIL, SAMPLE_SENDER_GMAIL, SAMPLE_SUBJECT, SAMPLE_TEXT);
        Assertions.assertEquals(expectedReturn, actualReturn);
    }
}
