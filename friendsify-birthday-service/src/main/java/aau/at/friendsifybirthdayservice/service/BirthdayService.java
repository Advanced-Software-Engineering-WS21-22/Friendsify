package aau.at.friendsifybirthdayservice.service;

import aau.at.friendsifybirthdayservice.exception.NoBirthdayException;
import aau.at.friendsifybirthdayservice.exception.ResourceNotFoundException;
import aau.at.friendsifybirthdayservice.obj.Friend;
import aau.at.friendsifybirthdayservice.obj.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BirthdayService {

    @Value("${birthday.range.days}")
    private int birthdayRange = 2;

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private PersonClient personClient;

    @Autowired
    private FriendClient friendClient;

    public void happyBirthday(Long personId, Long birthdayKidId) throws NoBirthdayException, ResourceNotFoundException {
        Person person = personClient.getPerson(personId);
        Person birthdayKid = personClient.getPerson(birthdayKidId);
        if (!LocalDate.now().equals(birthdayKid.getBirthday())) {
            throw new NoBirthdayException();
        }

        emailClient.sendBirthdayWish(person.getEmail(), birthdayKid.getEmail());
    }

    public List<Person> listBirthdayOfFriends(Long personId) {
        Person p = personClient.getPerson(personId);
        List<Person> friends = getFriendsOfPerson(p);
        return filterByBirthday(LocalDate.now(), friends);
    }

    public List<Person> listBirthdayKids() {
        return getBirthdayKidsOfDate(LocalDate.now());
    }

    public List<Person> filterByBirthday(LocalDate day, List<Person> persons) {
        return persons
                .stream()
                .filter(p -> {
                    LocalDate personsBirthday = p.getBirthday();
                    LocalDate limit = day.minusDays(birthdayRange);
                    return day.isEqual(personsBirthday) ||
                            (limit.isBefore(personsBirthday) && day.isAfter(personsBirthday));
                })
                .collect(Collectors.toList());
    }

    public void sendBirthdayReminders() {
        this.listBirthdayKids()
                .forEach(bdk -> this.getPersonsFriendOf(bdk)
                        .forEach(fop -> emailClient.sendBirthdayReminder(fop.getEmail(), bdk)));
    }

    private List<Person> getBirthdayKidsOfDate(LocalDate date) {
        List<Person> pl = personClient.getPersons();
        return filterByBirthday(date, pl);
    }

    private List<Person> getFriendsOfPerson(Person person) {
        List<Friend> fl = friendClient.getFriendsOfPerson(person);
        return personClient.getPersonsForFriendList(fl);
    }

    private List<Person> getPersonsFriendOf(Person person) {
        List<Friend> fl = friendClient.getPersonsThatAreFriendOf(person);
        return personClient.getPersonsForFriendList(fl);
    }
}
