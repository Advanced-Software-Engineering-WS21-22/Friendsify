package aau.at.friendsifyrecommendationsservice.unittests;

import aau.at.friendsifyrecommendationsservice.TestSamples;
import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Friends;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import aau.at.friendsifyrecommendationsservice.recommenderlogic.CommonFriendsRecommender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FriendsRecommenderTest {

    private Person[] all_persons;
    private Friends[] all_friends;

    private CommonFriendsRecommender commonFriendsRecommender;

    private Recommendation recommendation;

    @BeforeEach
    public void setUp() {
        this.all_persons = TestSamples.getPersonSamples();
        this.all_friends = TestSamples.getFriendsSamples();
        this.recommendation = new Recommendation();
    }

    @Test
    public void testRecommendedByCommonFriends() {
        this.commonFriendsRecommender = new CommonFriendsRecommender(this.all_persons, this.all_friends);
        commonFriendsRecommender.recommendedByCommonFriends(1L, recommendation);

        Assertions.assertEquals(this.all_persons[2].getEmail(), this.recommendation.getRecommendedByCommonFriends().getEmail());
        Assertions.assertTrue(this.recommendation.getCommonFriendsCount() == 2L);
    }

    @Test
    public void testRecommendedByCommonFriendsPersonNotFound() {
        this.commonFriendsRecommender = new CommonFriendsRecommender(this.all_persons, this.all_friends);

        Assertions.assertThrows(PersonNotFoundException.class,
                () ->  {
            commonFriendsRecommender.recommendedByCommonFriends(-1L, recommendation);
        });
    }
}
