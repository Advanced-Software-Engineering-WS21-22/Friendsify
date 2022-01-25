package aau.at.friendsifyfriendsservice.unittest;

import aau.at.friendsifyfriendsservice.businesslogic.BusinessLogicFriends;
import aau.at.friendsifyfriendsservice.controller.FriendsController;
import aau.at.friendsifyfriendsservice.model.Friends;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FriendsController.class)
@Log4j2
public class FriendsControllerTest {
//    private Friends defaultFriends;
//
//    @MockBean
//    private BusinessLogicFriends businessLogicFriends;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setUp(){
//        defaultFriends = new Friends(0L,false, "hans.m@gmail.com", "anna@gmx.at", new Date(2021,8,3));
//    }
//
//    @Test
//    public void listFriendshipsTest(){
//
//    }
//    @Test
//    public void getFriendshipByIdTest(){
//
//    }
//    @Test
//    public void getFriendshipsByEmailInitiatorTest(){
//
//    }
//    @Test
//    public void getFriendshipsByEmailFriendTest(){
//
//    }
//    @Test
//    public void getIDTest(){
//
//    }
//    @Test
//    public void createFriendshipTest(){
//
//    }
//    @Test
//    public void updateFriendshipTest(){
//
//    }
//    @Test
//    public void deleteFriendshipTest(){
//
//    }


}
