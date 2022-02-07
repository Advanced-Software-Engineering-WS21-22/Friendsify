package aau.at.friendsifyrecommendationsservice.recommenderlogic;

import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Friends;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;

import java.util.ArrayList;

public class CommonFriendsRecommender {

    private Person[] allPersons;

    private Friends[] allFriends;

    public CommonFriendsRecommender(Person[] allPersons, Friends[] allFriends) {
        this.allPersons = allPersons;
        this.allFriends = allFriends;
    }

    public void recommendedByCommonFriends(Long id_p, Recommendation recommendation) throws PersonNotFoundException {
        Person recommenderSubject = this.findPersonById(id_p);
        if(recommenderSubject != null) {
            this.findPersonWithMostCommonFriends(recommenderSubject.getEmail(), recommendation);
        } else {
            throw new PersonNotFoundException("Person not found");
        }
    }

    private Person findPersonById(Long id_p) {
        for (Person p: this.allPersons) {
            if(p.getId_p().equals(id_p)) {
                return p;
            }
        }
        return null;
    }

    private void findPersonWithMostCommonFriends(String subjectEmail, Recommendation recommendation) {
        ArrayList<String> friends_of_subject = this.getFriendsEmails(subjectEmail);
        Person found = null;
        int bestCommonFriendsCount = -1;

        for (Person person : this.allPersons) {
            if(!person.getEmail().equals(subjectEmail)) {
                ArrayList<String> friends_of_person = this.getFriendsEmails(person.getEmail());
                int commonFriendsCount = 0;

                for (String mail : friends_of_person) {
                    if(friends_of_subject.contains(mail) && !mail.equals(subjectEmail)) {
                        commonFriendsCount++;
                    }
                }

                if(commonFriendsCount > bestCommonFriendsCount) {
                    bestCommonFriendsCount = commonFriendsCount;
                    found = person;
                }
            }
        }

        recommendation.setRecommendedByCommonFriends(found);
        recommendation.setCommonFriendsCount(bestCommonFriendsCount);
    }

    private ArrayList<String> getFriendsEmails(String email) {
        ArrayList<String> friendsEmails = new ArrayList<String>();
        for (Friends fs : this.allFriends) {
            if(fs.getEmail_p_initiator().equals(email) && !friendsEmails.contains(fs.getEmail_p_friend())) {
                friendsEmails.add(fs.getEmail_p_friend());
            } else if (fs.getEmail_p_friend().equals(email) && !friendsEmails.contains(fs.getEmail_p_initiator())) {
                friendsEmails.add(fs.getEmail_p_initiator());
            }
        }
        return friendsEmails;
    }

}
