package aau.at.friendsifyrecommendationsservice.recommenderlogic;

import aau.at.friendsifyrecommendationsservice.models.Friends;
import aau.at.friendsifyrecommendationsservice.models.Person;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import aau.at.friendsifyrecommendationsservice.services.FriendsService;
import aau.at.friendsifyrecommendationsservice.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

public class RecommenderCoordinator {

    @Autowired
    private PersonService personService;

    @Autowired
    private FriendsService friendsService;

    private Person[] all_persons;
    private Friends[] all_friends;

    public Recommendation findRecommendation(Long id_p) {
        Recommendation recommendation = new Recommendation();

        this.all_persons = this.personService.getPersons();
        this.all_friends = this.friendsService.getFriends();

        AgeRecommender ageRecommender = new AgeRecommender(this.all_persons);
        CommonFriendsRecommender commonFriendsRecommender = new CommonFriendsRecommender(this.all_persons, this.all_friends);

        ageRecommender.recommended_by_age(id_p, recommendation);
        commonFriendsRecommender.recommended_by_common_friends(id_p, recommendation);


        return recommendation;
    }


}
