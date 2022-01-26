package aau.at.friendsifyjokeservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${email.host}")
    private String host;

    public EmailClient() {
        this.webClient = WebClient.builder().build();
    }

    public void send(String from, String to, String subject, String text) {
        Map<String, String> data = new HashMap<>();
        data.put("from", from);
        data.put("to", to);
        data.put("subject", subject);
        data.put("text", text);
        this.webClient
            .post()
            .uri(host)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(data))
            .retrieve()
            .bodyToMono(String.class)
            .block()
        ;
    }
}