package aau.at.anniversaryreminder.service;

import java.time.LocalDate;

public interface AnniversaryService {

    boolean isTodayAnniversary(String emailInitiator, String emailFriend);

    int daysUntilAnniversary(String emailInitiator, String emailFriend);

    String getAnniversaryReminder(String emailInitiator, String emailFriend);

}
