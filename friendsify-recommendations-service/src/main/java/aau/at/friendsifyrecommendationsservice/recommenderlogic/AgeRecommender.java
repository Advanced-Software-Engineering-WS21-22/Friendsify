package aau.at.friendsifyrecommendationsservice.recommenderlogic;

import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class AgeRecommender {

    private Person[] all_persons;

    public AgeRecommender(Person[] all_persons) {
        this.all_persons = all_persons;
    }

    public void recommended_by_age(Long id_p, Recommendation recommendation) throws PersonNotFoundException {
        Person recommender_subject = this.findPersonById(id_p);
        if(recommender_subject != null) {
            this.findMinimalAgeDifference(recommender_subject, recommendation);
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

    private void findMinimalAgeDifference(Person subject, Recommendation recommendation) {
        LocalDate subject_birthday = subject.getBirthday();
        Person found = null;
        Long best_day_difference = -1L;

        for (Person person : this.all_persons) {
            if(person.getId_p() != subject.getId_p()) {
                LocalDate person_birthday = person.getBirthday();
                Long day_difference = Math.abs(DAYS.between(subject_birthday, person_birthday));
                if ( best_day_difference == -1 || day_difference < best_day_difference) {
                    best_day_difference = day_difference;
                    found = person;
                }
            }
        }
        recommendation.setRecommended_by_age(found);
        recommendation.setAge_difference_days(best_day_difference);
    }

}
