package aau.at.friendsifyfriendsservice.unittest;

import aau.at.friendsifyfriendsservice.FriendsifyFriendsServiceApplication;
import aau.at.friendsifyfriendsservice.businesslogic.BusinessLogicFriends;
import aau.at.friendsifyfriendsservice.controller.FriendsController;
import aau.at.friendsifyfriendsservice.model.Friends;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(FriendsController.class)
@Log4j2
public class FriendsControllerTest {
    private Friends defaultFriends;
    private Friends updateFriends;
    private List<Friends> friendships = new ArrayList<>();
    private final String defaultEmailInitiator = "default@gmx.at";
    private final String defaultEmailFriend = "default_1@gmail.com";
    private final boolean default_is_timed_out = true;
    private final LocalDate default_fs_start_date = LocalDate.of(2021,12,12);
    private final Long default_id_friend = 0L;

    @MockBean
    private BusinessLogicFriends businessLogicFriends;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        defaultFriends = new Friends(0L,false, defaultEmailInitiator, defaultEmailFriend, LocalDate.of(2021,12,12));
        updateFriends = new Friends(6L,true,"tester_initiator@gmail.com", "friend@gmail.com", LocalDate.of(2001,1,1));
        friendships.add(defaultFriends);
    }
    @AfterEach
    public void setDown(){
        defaultFriends=null;
        friendships = null;
    }

    @Test
    public void listFriendshipsTest() throws Exception {
        List<Long> ids = new ArrayList<>();
        ids.add( friendships.get(0).getId_friend());
        List<Integer> idsNew = new ArrayList<>();

        for(Long i = 1L ;i<4;i++){
            Friends f = new Friends(i,default_is_timed_out,defaultEmailInitiator,defaultEmailFriend,default_fs_start_date);
            friendships.add(f);
            ids.add(i);
        }
        for(Long l: ids){
            idsNew.add(l.intValue());
        }

        Mockito.when(this.businessLogicFriends.getAllFriendship()).thenReturn(friendships);

        final String link = "/friends";

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(link)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(friendships.size())))
                .andExpect(jsonPath("$.[0].id_friend",is(defaultFriends.getId_friend().intValue())))
                .andExpect(jsonPath("$.[*].id_friend",is(idsNew)));

    }
    @Test
    public void getFriendshipByIdTest() throws Exception {
        Mockito.when(this.businessLogicFriends.getById(default_id_friend)).thenReturn(defaultFriends);

        final String link = "/friends/"+default_id_friend;

        this.mockMvc.perform(MockMvcRequestBuilders
               .get(link)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_friend",is(defaultFriends.getId_friend().intValue())))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[*]",hasSize(5)));
    }
    @Test
    public void getFriendshipsByEmailInitiatorTest() throws Exception {
        List<String> emailInitiatorList = new ArrayList<>();
        emailInitiatorList.add(defaultFriends.getEmail_p_initiator());


        for(Long i = 1L ;i<4;i++){
            Friends f = new Friends(i,default_is_timed_out,defaultEmailInitiator,defaultEmailFriend,default_fs_start_date);
            friendships.add(f);
            emailInitiatorList.add(f.getEmail_p_initiator());
        }

        Mockito.when(this.businessLogicFriends.getByEmailInitiator(defaultEmailInitiator)).thenReturn(friendships);

        final String link = "/friends/?email_initiator="+defaultEmailInitiator;

        this.mockMvc.perform(MockMvcRequestBuilders
        .get(link)
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].email_p_initiator", is(emailInitiatorList)));

    }
    @Test
    public void getFriendshipsByEmailFriendTest() throws Exception {
        List<String> emailFriendList = new ArrayList<>();
        emailFriendList.add(defaultFriends.getEmail_p_friend());


        for(Long i = 1L ;i<4;i++){
            Friends f = new Friends(i,default_is_timed_out,defaultEmailInitiator,defaultEmailFriend,default_fs_start_date);
            friendships.add(f);
            emailFriendList.add(f.getEmail_p_friend());
        }

        Mockito.when(this.businessLogicFriends.getByEmailFriend(defaultEmailFriend)).thenReturn(friendships);

        final String link = "/friends/?email_friend="+defaultEmailFriend;

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(link)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].email_p_friend", is(emailFriendList)));

    }
    @Test
    public void getIDTest() throws Exception {
        for(Long i = 1L ;i<4;i++){
            Friends f = new Friends(i,default_is_timed_out,defaultEmailFriend,defaultEmailInitiator,default_fs_start_date);
            friendships.add(f);
        }

        Mockito.when(this.businessLogicFriends.getID(defaultEmailInitiator,defaultEmailFriend)).thenReturn(defaultFriends.getId_friend());

        final String link = "/friends/?email_initiator="+defaultEmailInitiator+"&email_friend="+defaultEmailFriend;

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(link)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(defaultFriends.getId_friend().intValue())));


    }
    @Test
    public void createFriendshipTest() throws Exception {
        Mockito.when(this.businessLogicFriends.saveFriendship(defaultFriends)).thenReturn(defaultFriends);
        final String link = "/friends";

        String json = "{\n" +
                "    \"id_friend\": 5,\n" +
                "    \"email_p_initiator\": \"lariTest@gmail.com\",\n" +
                "    \"email_p_friend\": \"maier@test.com\",\n" +
                "    \"fs_start_date\": \"2020-12-12\",\n" +
                "    \"is_timed_out\": false \n" +
                "}";


        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
        .post(link)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertNotNull(result);

    }
    @Test
    public void updateFriendshipTest() throws Exception {
        String json = "{\n" +
                "    \"id_friend\": 6,\n" +
                "    \"email_p_initiator\": \"tester_initiator@gmail.com\",\n" +
                "    \"email_p_friend\": \"friend@gmail.com\",\n" +
                "    \"fs_start_date\": \"2001-1-1\",\n" +
                "    \"is_timed_out\": true \n" +
                "}";

        final String link = "/friends/"+default_id_friend;

        Mockito.when(this.businessLogicFriends.update(default_id_friend,updateFriends)).thenReturn(updateFriends);

        this.mockMvc.perform(MockMvcRequestBuilders
        .put(link)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void deleteFriendshipTest() throws Exception {
        Mockito.when(this.businessLogicFriends.delete(default_id_friend)).thenReturn("deleted");

        final  String link = "/friends/"+default_id_friend;

        this.mockMvc.perform(MockMvcRequestBuilders
        .delete(link))
                .andExpect(status().isOk());

    }


}
