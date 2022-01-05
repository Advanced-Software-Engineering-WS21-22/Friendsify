package aau.at.friendsifybirthdayservice.service;

import lombok.extern.slf4j.Slf4j;
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
