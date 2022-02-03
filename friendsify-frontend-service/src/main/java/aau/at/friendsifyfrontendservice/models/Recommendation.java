package aau.at.friendsifyfrontendservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recommendation {

    private Person recommendedByAge;
    private Long ageDifferenceInDays;

    private Person recommendedByCommonFriends;
    private int commonFriendsCount;


    public Recommendation() {

    }

    public Recommendation(Person recommendedByAge, Long ageDifferenceInDays, Person recommendedByCommonFriends, int commonFriendsCount) {
        this.recommendedByAge = recommendedByAge;
        this.ageDifferenceInDays = ageDifferenceInDays;
        this.recommendedByCommonFriends = recommendedByCommonFriends;
        this.commonFriendsCount = commonFriendsCount;
    }
}
