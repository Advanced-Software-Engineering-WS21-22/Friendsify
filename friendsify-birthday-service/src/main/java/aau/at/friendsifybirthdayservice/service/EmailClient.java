package aau.at.friendsifybirthdayservice.service;

import aau.at.friendsifybirthdayservice.obj.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmailClient {

    private final WebClient webClient;

    public EmailClient(WebClient.Builder webClientBuilder) {
        String host = System.getProperty("email.host");
        this.webClient = webClientBuilder.baseUrl(host).build();
    }

    public ResponseEntity<Void> sendBirthdayWish(String email) {
        return send(email, "Happy Birthday!");
    }

    public ResponseEntity<Void> sendBirthdayReminder(String email, Person birthdayKid) {
        return send(email,
"Your friend " + StringUtils.join(birthdayKid.getFirstName(), birthdayKid.getLastName()) + " has birthday today!");
    }

    public ResponseEntity<Void> send(String email, String text) {
        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("text", text);
        ResponseEntity<Void> resp = this.webClient
            .put()
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(data))
            .retrieve().toBodilessEntity()
            .block()
        ;
        return resp;
    }
}
