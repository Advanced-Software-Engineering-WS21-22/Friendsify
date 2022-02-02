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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(FriendsController.class)
@Log4j2
public class FriendsControllerTest {
    @MockBean
    private BusinessLogicFriends businessLogicFriends;

    @Autowired
    private MockMvc mockMvc;

    private Friends defaultFriends;
    private Friends defaultFriends_2;
    private Friends defaultFriends_3;
    private Friends friendshipToMyself;
    private Friends updateFriends;
    private List<Friends> friends;
    private final String default_email_initiator = "hans.m@gmail.com";
    private final String default_email_friend = "anna@gmx.at";
    private final boolean default_is_timed_out = false;
    private final LocalDate default_fs_start_date = LocalDate.of(2022,1,26);
    private final Long default_notValid_ID = 8L;
    private final String default_notValid_emailInitiator = "hansGuckInDieLuft@gmail.com";
    private final String default_notValid_emailFriend = "hansGuckInDieLuft@gmail.com";


    @BeforeEach
    public void setUp(){
        friendshipToMyself = new Friends(8L,true,"hello@gmx.at","hello@gmx.at", LocalDate.of(1000,2,2));
        defaultFriends = new Friends(0L, default_is_timed_out,default_email_initiator,default_email_friend, default_fs_start_date);
        defaultFriends_2 = new Friends(1L, true,"test@gamil.com","friendTest@aau.at",LocalDate.of(2006,8,5));
        defaultFriends_3 = new Friends(2L, default_is_timed_out, default_email_friend,default_email_initiator,default_fs_start_date);
        updateFriends = new Friends(0L,true, "hansi@gmail.com", "anni@gmx.at", LocalDate.of(6666,6,6));
        friends = new ArrayList<>();
        friends.add(defaultFriends);
        friends.add(defaultFriends_2);
        friends.add(defaultFriends_3);
    }
    @AfterEach
    public void setDown(){
        defaultFriends = null;
        defaultFriends_2 = null;
        defaultFriends_3 = null;
        friendshipToMyself = null;
        updateFriends = null;
        friends = null;
    }

    @Test
    public void listFriendshipsTest() throws Exception {
        when(this.businessLogicFriends.getAllFriendship()).thenReturn(friends);
        int defaultListSize = friends.size();
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/friends")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(defaultListSize)))
                .andExpect(jsonPath("$.[0].id_friend",is(defaultFriends.getId_friend().intValue())))
                .andExpect(jsonPath("$.[1].id_friend",is(defaultFriends_2.getId_friend().intValue())))
                .andExpect(jsonPath("$.[2].id_friend",is(defaultFriends_3.getId_friend().intValue())));

        verify(this.businessLogicFriends,times(1)).getAllFriendship();
    }
    @Test
    public void getFriendshipByIdTest() throws Exception {
        try {
            when(this.businessLogicFriends.getById(defaultFriends.getId_friend())).thenReturn(defaultFriends);
            final String link = "/friends/"+defaultFriends.getId_friend();

            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(link)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id_friend",is(defaultFriends.getId_friend().intValue())))
                    .andExpect(jsonPath("$").isNotEmpty())
                    .andExpect(jsonPath("$[*]",hasSize(5)));
        } catch (Exception e) {
            throw  new Exception("Should have found all friendships!");
        }
    }
    @Test
    public void getFriendshipByIdTest_Fail() throws Exception {
        when(this.businessLogicFriends.getById(default_notValid_ID)).thenThrow(new ResourceNotFoundException("Friendship not found for id: " + default_notValid_ID));

        final String link = "/friends/"+default_notValid_ID;

        try {
            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(link)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
            verify(this.businessLogicFriends,times(1)).getById(default_notValid_ID);
        } catch (Exception e) {
            throw new Exception("Http status should be Not Found.");
        }
    }


    @Test
    public void getFriendshipsByEmailInitiatorTest() throws Exception {
        List<Friends> expected = new ArrayList<>();
        expected.add(defaultFriends);
        try {
            when(this.businessLogicFriends.getByEmailInitiator(default_email_initiator)).thenReturn(expected);
            final String link = "/friends/?email_initiator="+default_email_initiator;
            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(link)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[*].email_p_initiator", containsInAnyOrder(default_email_initiator)))
                    .andExpect(jsonPath("$",hasSize(1)));
        } catch (Exception e) {
            throw  new Exception("Should only found one friendship, with email_initiator: "+default_email_initiator);
        }
    }
    @Test
    public void getFriendshipsByEmailInitiatorTest_Fail() throws Exception {
        when(this.businessLogicFriends.getByEmailInitiator(default_notValid_emailInitiator)).thenThrow(new ResourceNotFoundException("No Friendships with this initiator email found: "+default_notValid_emailInitiator));

        final String link = "/friends/?email_initiator="+default_notValid_emailInitiator;

        try {
            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(link)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
            verify(this.businessLogicFriends,times(1)).getByEmailInitiator(default_notValid_emailInitiator);
        } catch (Exception e) {
            throw new Exception("Http status should be Not Found.");
        }
    }
    @Test
    public void getFriendshipsByEmailFriendTest() throws Exception {
        List<Friends> expected = new ArrayList<>();
        expected.add(defaultFriends);
        try {
            when(this.businessLogicFriends.getByEmailFriend(default_email_friend)).thenReturn(expected);
            final String link = "/friends/?email_friend="+default_email_friend;
            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(link)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[*].email_p_friend", containsInAnyOrder(default_email_friend)))
                    .andExpect(jsonPath("$",hasSize(1)));
        } catch (Exception e) {
            throw  new Exception("Should only found one friendship, with email_friend: "+default_email_friend);
        }
    }
    @Test
    public void getFriendshipsByEmailFriendTest_Fail() throws Exception {
        when(this.businessLogicFriends.getByEmailFriend(default_notValid_emailFriend)).thenThrow(new ResourceNotFoundException("No Friendships with this friend email found: "+default_notValid_emailFriend));

        final String link = "/friends/?email_friend="+default_notValid_emailFriend;

        try {
            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(link)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
            verify(this.businessLogicFriends,times(1)).getByEmailFriend(default_notValid_emailFriend);
        } catch (Exception e) {
            throw new Exception("Http status should be Not Found.");
        }
    }
    @Test
    public void getIDTest() throws Exception {

        try {
            when(this.businessLogicFriends.getID(default_email_initiator,default_email_friend)).thenReturn(defaultFriends.getId_friend());
            final String link = "/friends/?email_initiator="+default_email_initiator+"&email_friend="+default_email_friend;

            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(link)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", is(defaultFriends.getId_friend().intValue())));

        } catch (Exception e) {
            throw  new Exception("Should find id: "+defaultFriends.getId_friend());
        }
    }
    @Test
    public void getIDTest_Fail() throws Exception {
        when(this.businessLogicFriends.getID(default_notValid_emailInitiator,default_notValid_emailFriend)).thenThrow(new ResourceNotFoundException("Friendship with email_initiator = "+default_notValid_emailInitiator+"and email_friend = "+default_notValid_emailFriend+"was not found."));

        final String link = "/friends/?email_initiator="+default_notValid_emailInitiator+"&email_friend="+default_notValid_emailFriend;

        try {
            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(link)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
            verify(this.businessLogicFriends,times(1)).getID(default_notValid_emailInitiator,default_notValid_emailFriend);
        } catch (Exception e) {
            throw new Exception("Http status should be Not Found.");
        }
    }


    @Test
    public void createFriendshipTest() throws Exception {
        when(this.businessLogicFriends.saveFriendship(defaultFriends)).thenReturn(defaultFriends);
        final String link = "/friends";

        String json = "{\n" +
                "\"id_friend\": 0,\n" +
                "\"email_p_initiator\": \"hans.m@gmail.com\",\n" +
                "\"email_p_friend\": \"anna@gmx.at\",\n" +
                "\"fs_start_date\": \"2022-01-26\",\n" +
                "\"is_timed_out\": false \n" +
                "}";



        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .post(link)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(result);
        assertEquals(json.replaceAll("[\\n\\t ]", ""), result.getResponse().getContentAsString());

    }
    @Test
    public void updateFriendshipTest() throws Exception {
        when(this.businessLogicFriends.update(defaultFriends.getId_friend(),updateFriends)).thenReturn(updateFriends);

        String json = "{\n" +
                "    \"id_friend\": 0,\n" +
                "    \"email_p_initiator\": \"hansi@gmail.com\",\n" +
                "    \"email_p_friend\": \"anni@gmx.at\",\n" +
                "    \"fs_start_date\": \"6666-06-06\",\n" +
                "    \"is_timed_out\": true \n" +
                "}";

        final String link = "/friends/"+defaultFriends.getId_friend();


        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .put(link)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }
    @Test
    public void deleteFriendshipTest() throws Exception {
        when(this.businessLogicFriends.delete(defaultFriends.getId_friend())).thenReturn("deleted");

        final  String link = "/friends/"+defaultFriends.getId_friend();

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(link))
                .andExpect(result -> equals("\"deleted\":true"))
                .andExpect(status().isOk());

    }
}
