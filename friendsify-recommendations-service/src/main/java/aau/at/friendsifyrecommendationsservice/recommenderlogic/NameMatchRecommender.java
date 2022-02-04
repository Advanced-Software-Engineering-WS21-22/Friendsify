package aau.at.friendsifyrecommendationsservice.recommenderlogic;

import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;

public class NameMatchRecommender {

    private Person[] allPersons;

    public NameMatchRecommender(Person[] allPersons) {
        this.allPersons = allPersons;
    }
    public void recommendByFirstName(Long id_p, Recommendation recommendation) throws PersonNotFoundException {
        Person recommenderSubject = this.findPersonById(id_p);
        if(recommenderSubject != null) {
            this.findFirstNameMatch(recommenderSubject, recommendation);
        } else {
            throw new PersonNotFoundException("Person not found");
        }
    }
    public void recommendByLastName(Long id_p, Recommendation recommendation) throws PersonNotFoundException{
        Person recommenderSubject = this.findPersonById(id_p);
        if(recommenderSubject != null) {
            this.findLastNameMatch(recommenderSubject, recommendation);
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
    private void findFirstNameMatch(Person subject, Recommendation recommendation)throws PersonNotFoundException{
        String firstNameSubject = subject.getFirst_name();
        Person found = null;

        for(Person p: this.allPersons){
            if(p.getFirst_name().equals(firstNameSubject)){
                found = p;
            }
        }
        if(found!=null){
            recommendation.setRecommendedByFirstName(found);
        }else {
            throw new PersonNotFoundException("Sorry, no FirstNameMatch found.");
        }
    }
    private void findLastNameMatch(Person subject, Recommendation recommendation)throws PersonNotFoundException{
        String lastNameSubject = subject.getLast_name();
        Person found = null;

        for(Person p: this.allPersons){
            if(p.getLast_name().equals(lastNameSubject)){
                found = p;
            }
        }
        if(found!=null){
            recommendation.setRecommendedByLastName(found);
        }else {
            throw  new PersonNotFoundException("Sorry, no LastNameMatch found.");
        }
    }
}
