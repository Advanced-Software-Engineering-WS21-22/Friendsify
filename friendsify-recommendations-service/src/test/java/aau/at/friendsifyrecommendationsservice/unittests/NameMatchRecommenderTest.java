package aau.at.friendsifyrecommendationsservice.unittests;

import aau.at.friendsifyrecommendationsservice.TestSamples;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import aau.at.friendsifyrecommendationsservice.recommenderlogic.NameMatchRecommender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameMatchRecommenderTest {
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
    void testNameMatchRecommenderFirstName_Fail() {
        this.nameMatchRecommender = new NameMatchRecommender(this.all_persons);
        this.nameMatchRecommender.recommendByFirstName(2L,this.recommendation);
        assertEquals(null, this.recommendation.getRecommendedByFirstName());
    }
    @Test
    void testNameMatchRecommenderFirstName() {
        this.nameMatchRecommender = new NameMatchRecommender(this.all_persons);
        this.all_persons[0].setFirst_name("Bob");
        this.nameMatchRecommender.recommendByFirstName(1L,this.recommendation);
        assertNotNull(this.recommendation.getRecommendedByFirstName());
        assertEquals(this.all_persons[4],this.recommendation.getRecommendedByFirstName());
    }
    @Test
    void testNameMatchRecommenderLastName_Fail(){
        this.nameMatchRecommender = new NameMatchRecommender(this.all_persons);
        this.nameMatchRecommender.recommendByLastName(4L,this.recommendation);
        assertEquals(null, this.recommendation.getRecommendedByLastName());
    }
    @Test
    void testNameMatchRecommenderLastName(){
        this.nameMatchRecommender = new NameMatchRecommender(this.all_persons);
        this.nameMatchRecommender.recommendByLastName(1L,this.recommendation);
        assertNotNull(this.recommendation.getRecommendedByLastName());
        assertEquals(this.all_persons[2],this.recommendation.getRecommendedByLastName());
    }

}
