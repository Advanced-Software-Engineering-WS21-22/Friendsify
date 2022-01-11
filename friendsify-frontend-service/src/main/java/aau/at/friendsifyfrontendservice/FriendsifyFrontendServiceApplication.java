package aau.at.friendsifyfrontendservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class FriendsifyFrontendServiceApplication {

    public static void main(String[] args) {
        System.out.println("Friendsify-Frontend Container running");
        SpringApplication.run(FriendsifyFrontendServiceApplication.class, args);
    }

}
