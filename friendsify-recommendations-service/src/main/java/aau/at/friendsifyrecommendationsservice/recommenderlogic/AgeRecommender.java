package aau.at.friendsifyrecommendationsservice.recommenderlogic;

import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class AgeRecommender {

    private Person[] allPersons;

    public AgeRecommender(Person[] allPersons) {
        this.allPersons = allPersons;
    }

    public void recommendedByAge(Long id_p, Recommendation recommendation) throws PersonNotFoundException {
        Person recommenderSubject = this.findPersonById(id_p);
        if(recommenderSubject != null) {
            this.findMinimalAgeDifference(recommenderSubject, recommendation);
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

    private void findMinimalAgeDifference(Person subject, Recommendation recommendation) {
        LocalDate subjectBirthday = subject.getBirthday();
        Person found = null;
        Long bestDayDifference = -1L;

        for (Person person : this.allPersons) {
            if(!person.getId_p().equals(subject.getId_p()) ) {
                LocalDate personBirthday = person.getBirthday();
                Long dayDifference = Math.abs(DAYS.between(subjectBirthday, personBirthday));
                if ( bestDayDifference == -1 || dayDifference < bestDayDifference) {
                    bestDayDifference = dayDifference;
                    found = person;
                }
            }
        }
        recommendation.setRecommendedByAge(found);
        recommendation.setAgeDifferenceInDays(bestDayDifference);
    }

}
