package aau.at.anniversaryreminder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AnniversaryServiceImpl implements AnniversaryService {

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private PersonService personService;

    @Override
    public boolean isTodayAnniversary(String emailInitiator, String emailFriend) {
        LocalDate today = LocalDate.now();
        LocalDate friendShipStart = friendsService.getFriendshipStartDate(emailInitiator, emailFriend);
        return today.isEqual(friendShipStart);
    }

    @Override
    public int daysUntilAnniversary(String emailInitiator, String emailFriend, LocalDate dateToCompare) {
        LocalDate friendShipStart = friendsService.getFriendshipStartDate(emailInitiator, emailFriend);
        int dayAnniversary = friendShipStart.getDayOfYear();
        int dayToCompare = dateToCompare.getDayOfYear();
        if (dayAnniversary >= dayToCompare) {
            return dayAnniversary - dayToCompare;
        } else {
            return 365 - (dayToCompare - dayAnniversary);
        }
    }

    @Override
    public String getAnniversaryReminder(String emailInitiator, String emailFriend) {
        String nameInitiator = personService.getNameByEmail(emailInitiator);
        String nameFriend = personService.getNameByEmail(emailFriend);
        LocalDate friendshipStartDate = friendsService.getFriendshipStartDate(emailInitiator, emailFriend);

        LocalDate today = LocalDate.now();
        int friendshipYears = friendshipStartDate.until(today).getYears();
        return nameInitiator + " celebrates the " + friendshipYears + " year anniversary of the friendship to " + nameFriend;
    }
}
