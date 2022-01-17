package aau.at.friendsifyemailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("SendService")
public class EmailServiceImpl implements EmailService {

    private static final String NO_REPLY = "noreply@friendsify.com";

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendEmail(String to, String from, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NO_REPLY);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text + "\n\nSend from user: " + from);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
}
