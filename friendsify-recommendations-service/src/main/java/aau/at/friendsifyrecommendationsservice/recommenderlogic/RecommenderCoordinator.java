package aau.at.friendsifyrecommendationsservice.recommenderlogic;

import aau.at.friendsifyrecommendationsservice.models.Friends;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import aau.at.friendsifyrecommendationsservice.services.FriendsService;
import aau.at.friendsifyrecommendationsservice.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommenderCoordinator {

    @Autowired
    private PersonService personService;

    @Autowired
    private FriendsService friendsService;

    private Person[] allPersons;
    private Friends[] allFriends;

    public Recommendation findRecommendation(Long id_p) {
        Recommendation recommendation = new Recommendation();

        this.allPersons = this.personService.getPersons();
        this.allFriends = this.friendsService.getFriends();

        AgeRecommender ageRecommender = new AgeRecommender(this.allPersons);
        CommonFriendsRecommender commonFriendsRecommender = new CommonFriendsRecommender(this.allPersons, this.allFriends);

        ageRecommender.recommendedByAge(id_p, recommendation);
        commonFriendsRecommender.recommendedByCommonFriends(id_p, recommendation);


        return recommendation;
    }


}
