package aau.at.friendsifypersonservice;

import controllers.PersonController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = PersonController.class)
public class FriendsifyPersonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendsifyPersonServiceApplication.class, args);
    }

}
