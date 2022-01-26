package aau.at.friendsifyemailservice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static aau.at.friendsifyemailservice.ConfigurationProperties.*;

public class EmailServiceUnitTests {

    private JavaMailSender mailSenderMock;
    private EmailService emailService;
    private SimpleMailMessage message;

    @BeforeEach
    public void init() {
        this.mailSenderMock = Mockito.mock(JavaMailSender.class);
        this.emailService = new EmailServiceImpl(mailSenderMock);
        this.message = new SimpleMailMessage();
        this.message.setFrom(EmailServiceImpl.NO_REPLY);
        this.message.setTo(SAMPLE_RECEIVER_GMAIL);
        this.message.setSubject(SAMPLE_SUBJECT);
        this.message.setText(SAMPLE_TEXT + SEND_FROM_USER_TEXT + SAMPLE_SENDER_GMAIL);
    }

    @AfterEach
    public void tearDown() {
        this.emailService = null;
        this.mailSenderMock = null;
        this.message = null;
    }


    @Test
    public void testSimpleEmailSendRequest() {
        this.emailService.sendEmail(SAMPLE_RECEIVER_GMAIL, SAMPLE_SENDER_GMAIL, SAMPLE_SUBJECT, SAMPLE_TEXT);
        Mockito.verify(this.mailSenderMock, Mockito.times(1)).send(this.message);
    }

}
