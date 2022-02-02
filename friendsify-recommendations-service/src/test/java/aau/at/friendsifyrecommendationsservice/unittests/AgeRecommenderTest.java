package aau.at.friendsifyrecommendationsservice.unittests;

import aau.at.friendsifyrecommendationsservice.TestSamples;
import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import aau.at.friendsifyrecommendationsservice.recommenderlogic.AgeRecommender;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class AgeRecommenderTest {

    private Person[] all_persons;

    private AgeRecommender ageRecommender;

    private Recommendation recommendation;

    @BeforeEach
    public void setUp() {
        this.all_persons = TestSamples.getPersonSamples();
        this.recommendation = new Recommendation();
    }

    @Test
    public void testAgeRecommender() {
        this.ageRecommender = new AgeRecommender(this.all_persons);
        ageRecommender.recommended_by_age(1L, this.recommendation);

        Assert.assertEquals(this.all_persons[3], this.recommendation.getRecommended_by_age());
        Assert.assertTrue(this.recommendation.getAge_difference_days() == 1L);
    }

    @Test
    public void testAgeRecommenderPersonNotFound() {
        this.ageRecommender = new AgeRecommender(this.all_persons);

        Assertions.assertThrows(PersonNotFoundException.class,
                () ->  {
            ageRecommender.recommended_by_age(-1L, this.recommendation);
        });
    }
}
