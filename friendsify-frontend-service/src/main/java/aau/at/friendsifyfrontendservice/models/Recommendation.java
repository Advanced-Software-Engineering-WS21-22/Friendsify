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

    private Person recommendedByFirstName;
    private Person recommendedByLastName;


    public Recommendation() {

    }

    public Recommendation(Person recommendedByAge, Long ageDifferenceInDays, Person recommendedByCommonFriends, int commonFriendsCount, Person recommendedByFirstName, Person recommendedByLastName) {
        this.recommendedByAge = recommendedByAge;
        this.ageDifferenceInDays = ageDifferenceInDays;
        this.recommendedByCommonFriends = recommendedByCommonFriends;
        this.commonFriendsCount = commonFriendsCount;
        this.recommendedByFirstName = recommendedByFirstName;
        this.recommendedByLastName = recommendedByLastName;
    }
}
