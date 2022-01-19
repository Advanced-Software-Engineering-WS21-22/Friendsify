package aau.at.friendsifybirthdayservice.task;

import aau.at.friendsifybirthdayservice.obj.Person;
import aau.at.friendsifybirthdayservice.service.EmailClient;
import aau.at.friendsifybirthdayservice.service.FriendClient;
import aau.at.friendsifybirthdayservice.service.PersonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class BirthdayReminderTask {

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private PersonClient personClient;

    @Autowired
    private FriendClient friendClient;

    @Scheduled(fixedRate = 90, timeUnit = TimeUnit.SECONDS)
    public void sendReminder() {
        List<Person> birthdayKids = personClient.getTodaysBirthdayKids();
        for(Person birthdayKid : birthdayKids) {
            List<Person> friendsOfBirthdayKid = friendClient.getFriendOfPerson(birthdayKid);
            for (Person friendOfBirthdayKid : friendsOfBirthdayKid) {
                emailClient.sendBirthdayReminder(friendOfBirthdayKid.getEmail(), birthdayKid);
            }
        }
    }
}
