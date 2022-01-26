package aau.at.friendsifybirthdayservice.service;

import aau.at.friendsifybirthdayservice.constants.EmailConstants;
import aau.at.friendsifybirthdayservice.obj.Email;
import aau.at.friendsifybirthdayservice.obj.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EmailClient {

    private final RestTemplate rt;

    @Value("${email.host.url}")
    private String host;

    public EmailClient() {
        this.rt = new RestTemplateBuilder().build();
    }

    public EmailClient(String host) {
        this();
        this.host = host;
    }

    public String send(Email email) {
        return this.rt.postForObject(host, email, String.class);
    }

    public String sendBirthdayWish(String from, String to) {
         return send(new Email(from, to, EmailConstants.BIRTHDAY_SUBJECT, EmailConstants.BIRTHDAY_WISH));
    }

    public String sendBirthdayReminder(String to, Person birthdayKid) {
        return send(new Email(EmailConstants.DEFAULT_FROM, to, EmailConstants.BIRTHDAY_REMINDER,
                String.format(EmailConstants.BIRTHDAY_REMINDER_TEXT, birthdayKid.getFullName())
        ));
    }

}
