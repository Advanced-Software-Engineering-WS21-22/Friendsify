package aau.at.friendsifyfriendsservice.unittest;

import aau.at.friendsifyfriendsservice.businesslogic.BusinessLogicFriends;
import aau.at.friendsifyfriendsservice.controller.FriendsController;
import aau.at.friendsifyfriendsservice.exceptions.ResourceNotFoundException;
import aau.at.friendsifyfriendsservice.model.Friends;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
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
    private String defaultEmailInitiator = "default@gmx.at";
    private String defaultEmailFriend = "default_1@gmail.com";
    private boolean default_is_timed_out = true;
    private LocalDate default_fs_start_date = LocalDate.of(2021,12,12);
    private Long default_id_friend = 0L;

    @MockBean
    private BusinessLogicFriends businessLogicFriends;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        defaultFriends = new Friends(0L,false, defaultEmailInitiator, defaultEmailFriend, LocalDate.of(2021,12,12));
    }
    @AfterEach
    public void setDown(){
        defaultFriends=null;
    }

    @Test
    public void listFriendshipsTest() throws Exception {
        List<Friends> friendships = new ArrayList<>();
        friendships.add(defaultFriends);

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
        List<Friends> friendships = new ArrayList<>();
        friendships.add(defaultFriends);

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
        List<Friends> friendships = new ArrayList<>();
        friendships.add(defaultFriends);

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
        List<Friends> friendships = new ArrayList<>();
        friendships.add(defaultFriends);

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
        List<Friends> friendships = new ArrayList<>();
        friendships.add(defaultFriends);

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
    public void createFriendshipTest(){

    }
    @Test
    public void updateFriendshipTest(){

    }
    @Test
    public void deleteFriendshipTest(){

    }


}
