package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.inputs.FriendsInput;
import aau.at.friendsifyfrontendservice.models.Friends;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FriendsService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.friends}")
    private String friendsServiceEndpoint;

    public FriendsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Friends[] getFriends() {
        return restTemplate.getForObject(friendsServiceEndpoint, Friends[].class);
    }

    public Friends getFriendsByID(Long id_fs) {
        return restTemplate.getForObject(friendsServiceEndpoint+"/"+id_fs, Friends.class);
    }

    //Returns Friendship where person is the initiator
    public Friends[] getFriendsByInitiator(String email) {
        return restTemplate.getForObject(friendsServiceEndpoint + "?email_initiator=" + email, Friends[].class);
    }

    //Returns Friendship where person is the friend
    public Friends[] getFriendsByReceiver(String email) {
        return restTemplate.getForObject(friendsServiceEndpoint + "?email_friend=" + email, Friends[].class);
    }

    public void addFriends(FriendsInput friendsForm) {
        Friends friends = Friends.fromFriendsInput(friendsForm);
        restTemplate.postForObject(friendsServiceEndpoint, friends, Friends.class);
    }

    public void cancelFriendship(Long id_fs) {
        restTemplate.delete(friendsServiceEndpoint + "/" + id_fs);
    }

    public void updateFriends(Friends friends) {
        restTemplate.put(this.friendsServiceEndpoint+"/"+friends.getId_friend(), friends);
    }
}
