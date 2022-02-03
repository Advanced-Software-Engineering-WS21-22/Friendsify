package aau.at.friendsifyfrontendservice.services;

import aau.at.friendsifyfrontendservice.inputs.FriendsInput;
import aau.at.friendsifyfrontendservice.models.Friends;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class FriendsService {

    private final RestTemplate restTemplate;

    @Value("${endpoint.friends}")
    private String friendsServiceEndpoint;

    public FriendsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Friends[] getFriends() {
        Friends[] friends = null;
        friends = restTemplate.getForObject(friendsServiceEndpoint, Friends[].class);
        return friends;
    }

    //Returns Friendship where person is the initiator
    public Friends[] getFriendsByInitiator(String email) {
        Friends[] friends = null;
        friends = restTemplate.getForObject(friendsServiceEndpoint + "?email_initiator=" + email, Friends[].class);
        return friends;
    }

    //Returns Friendship where person is the friend
    public Friends[] getFriendsByReceiver(String email) {
        Friends[] friends = null;
        friends = restTemplate.getForObject(friendsServiceEndpoint + "?email_friend=" + email, Friends[].class);
        return friends;
    }

    public void addFriends(FriendsInput friendsForm) {
        Friends friends = Friends.fromFriendsInput(friendsForm);
        restTemplate.postForObject(friendsServiceEndpoint, friends, Friends.class);
    }

    public void cancelFriendship(Long id_fs) {
        restTemplate.delete(friendsServiceEndpoint + "/" + id_fs);
    }

    public void updateFriends(Friends friends) {
        restTemplate.put(this.friendsServiceEndpoint+"/"+friends.getId_friend().toString(), friends);
    }
}
