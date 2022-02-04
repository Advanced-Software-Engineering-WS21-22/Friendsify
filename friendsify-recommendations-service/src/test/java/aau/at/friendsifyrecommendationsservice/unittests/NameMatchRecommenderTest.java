package aau.at.friendsifyrecommendationsservice.unittests;

import aau.at.friendsifyrecommendationsservice.TestSamples;
import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import aau.at.friendsifyrecommendationsservice.recommenderlogic.NameMatchRecommender;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NameMatchRecommenderTest {
    private Person[] all_persons;

    private NameMatchRecommender nameMatchRecommender;

    private Recommendation recommendation;

    private String firstNameException = "Sorry, no FirstNameMatch found.";

    private String lastNameException = "Sorry, no LastNameMatch found.";

    @BeforeEach
    public void setUp(){
        this.all_persons = TestSamples.getPersonSamples();
        this.recommendation = new Recommendation();
    }
    @AfterEach
    public void tearDown(){
        this.all_persons = null;
        this.recommendation = null;
    }
    @Test
    public void testNameMatchRecommenderFirstName_Fail() {
        this.nameMatchRecommender = new NameMatchRecommender(this.all_persons);
        PersonNotFoundException exception = Assert.assertThrows(PersonNotFoundException.class, ()-> this.nameMatchRecommender.recommendByFirstName(1L,this.recommendation));
        assertEquals(firstNameException,exception.getMessage());
    }
    @Test
    public void testNameMatchRecommenderFirstName() {
        this.nameMatchRecommender = new NameMatchRecommender(this.all_persons);
        this.all_persons[0].setFirst_name("Bob");
        this.nameMatchRecommender.recommendByFirstName(1L,this.recommendation);
        assertNotNull(this.recommendation.getRecommendedByFirstName());
        assertEquals(this.all_persons[4],this.recommendation.getRecommendedByFirstName());
    }
    @Test
    public void testNameMatchRecommenderLastName_Fail(){
        this.nameMatchRecommender = new NameMatchRecommender(this.all_persons);
        PersonNotFoundException exception = Assert.assertThrows(PersonNotFoundException.class, ()-> this.nameMatchRecommender.recommendByLastName(2L,this.recommendation));
        assertEquals(lastNameException,exception.getMessage());
    }
    @Test
    public void testNameMatchRecommenderLastName(){
        this.nameMatchRecommender = new NameMatchRecommender(this.all_persons);
        this.nameMatchRecommender.recommendByLastName(1L,this.recommendation);
        assertNotNull(this.recommendation.getRecommendedByLastName());
        assertEquals(this.all_persons[2],this.recommendation.getRecommendedByLastName());
    }

}
