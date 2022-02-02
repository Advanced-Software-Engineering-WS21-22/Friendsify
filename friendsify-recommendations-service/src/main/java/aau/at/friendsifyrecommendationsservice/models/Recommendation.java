package aau.at.friendsifyrecommendationsservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recommendation {

    private Person recommended_by_age;
    private Long age_difference_days;

    private Person recommended_by_common_friends;
    private int common_friends_count;


    public Recommendation() {

    }

    public Recommendation(Person recommended_by_age, Long age_difference_day, Person recommended_by_common_friends, int common_friends_count) {
        this.recommended_by_age = recommended_by_age;
        this.age_difference_days = age_difference_day;
        this.recommended_by_common_friends = recommended_by_common_friends;
        this.common_friends_count = common_friends_count;
    }
}
