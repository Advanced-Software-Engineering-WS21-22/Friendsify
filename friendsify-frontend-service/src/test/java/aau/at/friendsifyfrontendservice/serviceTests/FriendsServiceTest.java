package aau.at.friendsifyfrontendservice.serviceTests;

import aau.at.friendsifyfrontendservice.inputs.FriendsInput;
import aau.at.friendsifyfrontendservice.models.Friends;
import aau.at.friendsifyfrontendservice.services.FriendsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.time.LocalDate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(FriendsService.class)
class FriendsServiceTest {

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    private String email_person_initiator;
    private String email_person_friend;
    private Friends friends;
    private FriendsInput friendsInput;
    private Friends[] allFriends;
    private Long id_fs;

    @Value("${endpoint.friends}")
    private String friendsServiceEndpoint;

    @BeforeEach
    public void setUp() {
        this.email_person_friend = "anna@mustermann.de";
        this.email_person_initiator = "max@mustermann.de";
        this.friends = new Friends(0L, this.email_person_initiator, this.email_person_friend, LocalDate.now(), false);
        this.friendsInput = new FriendsInput(this.email_person_initiator, this.email_person_friend, false);
        this.allFriends = new Friends[1];
        this.allFriends[0] = friends;
        this.id_fs = 0L;
    }

    @AfterEach
    public void tearDown() {
        this.email_person_friend = null;
        this.email_person_initiator = null;
        this.friends = null;
        this.friendsInput = null;
        this.allFriends = null;
        this.id_fs = null;
    }

    @Test
    void getAll() throws JsonProcessingException {
        String allFriends = objectMapper.writeValueAsString(this.allFriends);
        this.server.expect(requestTo(friendsServiceEndpoint)).andRespond(withSuccess(allFriends, MediaType.APPLICATION_JSON));

        Friends[] allFriendsFound = this.friendsService.getFriends();
        Assertions.assertEquals(this.allFriends[0].getId_friend(), allFriendsFound[0].getId_friend());
    }

    @Test
    void getByInitiator() throws JsonProcessingException {
        String friends = objectMapper.writeValueAsString(this.allFriends);
        this.server.expect(requestTo(friendsServiceEndpoint+"?email_initiator="+this.email_person_initiator)).andRespond(withSuccess(friends, MediaType.APPLICATION_JSON));

        Friends[] friendsFound = this.friendsService.getFriendsByInitiator(this.email_person_initiator);
        Assertions.assertEquals(this.allFriends[0].getId_friend(), friendsFound[0].getId_friend());
    }

    @Test
    void getBFriend() throws JsonProcessingException {
        String friends = objectMapper.writeValueAsString(this.allFriends);
        this.server.expect(requestTo(friendsServiceEndpoint+"?email_friend="+this.email_person_friend)).andRespond(withSuccess(friends, MediaType.APPLICATION_JSON));

        Friends[] friendsFound = this.friendsService.getFriendsByReceiver(this.email_person_friend);
        Assertions.assertEquals(this.allFriends[0].getId_friend(), friendsFound[0].getId_friend());
    }

    @Test
    void addFriends() {
        this.server.expect(requestTo(friendsServiceEndpoint)).andExpect(method(HttpMethod.POST)).andRespond(withSuccess());
        this.friendsService.addFriends(this.friendsInput);
    }
    @Test
    void deleteFriends() {
        this.server.expect(requestTo(friendsServiceEndpoint+"/"+this.id_fs)).andExpect(method(HttpMethod.DELETE)).andRespond(withSuccess());
        this.friendsService.cancelFriendship(this.id_fs);
    }

}
