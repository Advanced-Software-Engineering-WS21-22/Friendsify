package aau.at.friendsifyfrontendservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FriendsifyFrontendServiceApplication {

    public static void main(String[] args) {
        System.out.println("Friendsify-Frontend Container running");
        SpringApplication.run(FriendsifyFrontendServiceApplication.class, args);
    }

}
