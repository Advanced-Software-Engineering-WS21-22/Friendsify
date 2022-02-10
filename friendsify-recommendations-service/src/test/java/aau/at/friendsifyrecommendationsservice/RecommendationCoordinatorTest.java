package aau.at.friendsifyrecommendationsservice;

import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import aau.at.friendsifyrecommendationsservice.recommenderlogic.RecommenderCoordinator;
import aau.at.friendsifyrecommendationsservice.services.FriendsService;
import aau.at.friendsifyrecommendationsservice.services.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RecommendationCoordinatorTest {

    @Mock
    private PersonService personService;

    @Mock
    private FriendsService friendsService;

    @InjectMocks
    private RecommenderCoordinator recommenderCoordinator;

    @BeforeEach
    public void setUp() {
        this.recommenderCoordinator = new RecommenderCoordinator();
    }

    @Test
    public void testFindRecommendation() {
        Mockito.when(personService.getPersons()).thenReturn(TestSamples.getPersonSamples());
        Mockito.when(friendsService.getFriends()).thenReturn(TestSamples.getFriendsSamples());

        Recommendation recommendation = this.recommenderCoordinator.findRecommendation(1L);
        Assertions.assertEquals(Recommendation.class, recommendation.getClass());
    }

    @Test
    public void testFindRecommendationNoPersonFound() {
        Mockito.when(personService.getPersons()).thenReturn(TestSamples.getPersonSamples());
        Mockito.when(friendsService.getFriends()).thenReturn(TestSamples.getFriendsSamples());

        Assertions.assertThrows(PersonNotFoundException.class,
                () ->  {
                    this.recommenderCoordinator.findRecommendation(-1L);
                });
    }
}
