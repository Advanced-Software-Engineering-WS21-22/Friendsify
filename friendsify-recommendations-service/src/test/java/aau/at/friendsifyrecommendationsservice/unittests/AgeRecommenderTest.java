package aau.at.friendsifyrecommendationsservice.unittests;

import aau.at.friendsifyrecommendationsservice.TestSamples;
import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import aau.at.friendsifyrecommendationsservice.recommenderlogic.AgeRecommender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AgeRecommenderTest {

    private Person[] all_persons;

    private AgeRecommender ageRecommender;

    private Recommendation recommendation;

    @BeforeEach
    public void setUp() {
        this.all_persons = TestSamples.getPersonSamples();
        this.recommendation = new Recommendation();
    }

    @Test
    void testAgeRecommender() {
        this.ageRecommender = new AgeRecommender(this.all_persons);
        ageRecommender.recommendedByAge(1L, this.recommendation);

        Assertions.assertEquals(this.all_persons[3], this.recommendation.getRecommendedByAge());
        Assertions.assertEquals(1L,this.recommendation.getAgeDifferenceInDays());
    }

    @Test
    void testAgeRecommenderPersonNotFound() {
        this.ageRecommender = new AgeRecommender(this.all_persons);

        Assertions.assertThrows(PersonNotFoundException.class,
                () ->  {
            ageRecommender.recommendedByAge(-1L, this.recommendation);
        });
    }
}
