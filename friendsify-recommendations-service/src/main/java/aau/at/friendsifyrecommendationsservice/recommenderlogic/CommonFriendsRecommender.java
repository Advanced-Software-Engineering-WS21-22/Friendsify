package aau.at.friendsifyrecommendationsservice.recommenderlogic;

import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Friends;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;

import java.util.ArrayList;

public class CommonFriendsRecommender {

    private Person[] all_persons;

    private Friends[] all_friends;

    public CommonFriendsRecommender(Person[] all_persons, Friends[] all_friends) {
        this.all_persons = all_persons;
        this.all_friends = all_friends;
    }

    public void recommended_by_common_friends(Long id_p, Recommendation recommendation) throws PersonNotFoundException {
        Person recommender_subject = this.findPersonById(id_p);
        if(recommender_subject != null) {
            this.findPersonWithMostCommonFriends(recommender_subject.getEmail(), recommendation);
        } else {
            throw new PersonNotFoundException("Person not found");
        }
    }

    private Person findPersonById(Long id_p) {
        for (Person p: this.all_persons) {
            if(p.getId_p() == id_p) {
                return p;
            }
        }
        return null;
    }

    private void findPersonWithMostCommonFriends(String subject_email, Recommendation recommendation) {
        ArrayList<String> friends_of_subject = this.getFriendsEmails(subject_email);
        Person found = null;
        int best_common_friends_count = -1;

        for (Person person : this.all_persons) {
            if(!person.getEmail().equals(subject_email)) {
                ArrayList<String> friends_of_person = this.getFriendsEmails(person.getEmail());
                int common_friends_count = 0;

                for (String mail : friends_of_person) {
                    if(friends_of_subject.contains(mail) && !mail.equals(subject_email)) {
                        common_friends_count++;
                    }
                }

                if(common_friends_count > best_common_friends_count) {
                    best_common_friends_count = common_friends_count;
                    found = person;
                }
            }
        }

        recommendation.setRecommended_by_common_friends(found);
        recommendation.setCommon_friends_count(best_common_friends_count);
    }

    private ArrayList<String> getFriendsEmails(String email) {
        ArrayList<String> friendsEmails = new ArrayList<String>();
        for (Friends fs : this.all_friends) {
            if(fs.getEmail_p_initiator().equals(email) && !friendsEmails.contains(fs.getEmail_p_friend())) {
                friendsEmails.add(fs.getEmail_p_friend());
            } else if (fs.getEmail_p_friend().equals(email) && !friendsEmails.contains(fs.getEmail_p_initiator())) {
                friendsEmails.add(fs.getEmail_p_initiator());
            }
        }
        return friendsEmails;
    }

}
