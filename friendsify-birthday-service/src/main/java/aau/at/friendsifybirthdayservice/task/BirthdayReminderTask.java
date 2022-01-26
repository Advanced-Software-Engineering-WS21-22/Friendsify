package aau.at.friendsifybirthdayservice.task;

import aau.at.friendsifybirthdayservice.service.BirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BirthdayReminderTask {

    @Autowired
    private BirthdayService birthdayService;

    @Scheduled(fixedRateString = "${birthday.reminder.rate.seconds}", timeUnit = TimeUnit.SECONDS, initialDelay = 30)
    public void sendReminder() {
        birthdayService.sendBirthdayReminders();
    }
}
