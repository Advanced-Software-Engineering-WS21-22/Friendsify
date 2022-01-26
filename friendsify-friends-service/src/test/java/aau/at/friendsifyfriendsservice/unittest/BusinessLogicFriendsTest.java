package aau.at.friendsifyfriendsservice.unittest;

import aau.at.friendsifyfriendsservice.businesslogic.BusinessLogicFriends;
import aau.at.friendsifyfriendsservice.exceptions.InvalidDataException;
import aau.at.friendsifyfriendsservice.exceptions.ResourceNotFoundException;
import aau.at.friendsifyfriendsservice.model.Friends;
import aau.at.friendsifyfriendsservice.repositories.FriendsDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BusinessLogicFriendsTest {
    private Friends defaultFriends;
    private String default_email_initiator = "hans.m@gmail.com";
    private String default_email_friend = "anna@gmx.at";
    private boolean default_is_timed_out = false;
    private LocalDate default_fs_satrt_date = LocalDate.of(2022,1,26);

    @Mock
    private FriendsDao friendsDao;

    @InjectMocks
    private BusinessLogicFriends businessLogicFriends;

    @BeforeEach
    public void setup(){
        defaultFriends = new Friends(0L, default_is_timed_out,default_email_initiator,default_email_friend,default_fs_satrt_date);
    }
    @AfterEach
    public void tearDown(){
        defaultFriends = null;
    }

    private List<Friends> indicateGetAllFriendships(){
        List<Friends> friends = new ArrayList<>();
        friends.add(defaultFriends);

        Mockito.when(this.friendsDao.findAll()).thenReturn(friends);

        List<Friends> friendsList = this.businessLogicFriends.getAllFriendship();
        return friendsList;
    }
    @Test
    public void getAllFriendshipTest_NotNull(){
        Assertions.assertNotNull(indicateGetAllFriendships());
    }
    @Test
    public void getAllFriendshipTest_Size1(){
        Assertions.assertEquals(1, indicateGetAllFriendships().size());
    }
    @Test
    public void getAllFriendshipTest_Equals(){
        List<Friends> friends = new ArrayList<>();
        friends.add(defaultFriends);
        Assertions.assertEquals(friends, indicateGetAllFriendships());
    }

    private List<Friends> indicateGetByEmailInitiator() throws ResourceNotFoundException {
        List<Friends> friends = new ArrayList<>();
        friends.add(defaultFriends);

        Mockito.when(this.friendsDao.findAll()).thenReturn(friends);

        List<Friends> friendsList = this.businessLogicFriends.getByEmailInitiator(default_email_initiator);
        return friendsList;
    }
    @Test
    public void getByEmailInitiatorTest_NotNull() throws ResourceNotFoundException {
        Assertions.assertNotNull(indicateGetByEmailInitiator());

    }
    @Test
    public void getByEmailInitiatorTest_Size1() throws ResourceNotFoundException {
        Assertions.assertEquals(1,indicateGetByEmailInitiator().size());

    }
    @Test
    public void getByEmailInitiatorTest_Equals() throws ResourceNotFoundException {
        List<Friends> friends = new ArrayList<>();
        friends.add(defaultFriends);
        Assertions.assertEquals(friends,indicateGetByEmailInitiator());

    }
    private List<Friends> indicateGetByEmailFriend() throws ResourceNotFoundException {
        List<Friends> friends = new ArrayList<>();
        friends.add(defaultFriends);

        Mockito.when(this.friendsDao.findAll()).thenReturn(friends);

        List<Friends> friendsList = this.businessLogicFriends.getByEmailFriend(default_email_friend);
        return friendsList;
    }
    @Test
    public void getByEmailFriendTest_NotNull() throws ResourceNotFoundException {
        Assertions.assertNotNull(indicateGetByEmailFriend());
    }
    @Test
    public void getByEmailFriendTest_Size1() throws ResourceNotFoundException {
        Assertions.assertEquals(1,indicateGetByEmailFriend().size());
    }
    @Test
    public void getByEmailFriendTest_Equals() throws ResourceNotFoundException {
        List<Friends> friends = new ArrayList<>();
        friends.add(defaultFriends);
        Assertions.assertEquals(friends,indicateGetByEmailFriend());
    }

    @Test
    public void getIDTest() throws ResourceNotFoundException {
        List<Friends> friends = new ArrayList<>();
        friends.add(defaultFriends);

        Mockito.when(this.friendsDao.findAll()).thenReturn(friends);

        Long id = this.businessLogicFriends.getID(default_email_initiator,default_email_friend);

        Assertions.assertEquals(0L,id);
    }

    @Test
    public void saveFriendshipTest() throws InvalidDataException {
        Mockito.when(this.friendsDao.save(defaultFriends)).thenReturn(defaultFriends);

        Friends f = this.businessLogicFriends.saveFriendship(defaultFriends);

        Assertions.assertEquals(f,defaultFriends);
    }

    @Test
    public void updateTest() throws InvalidDataException, ResourceNotFoundException {
        Friends f = new Friends(0L,true, "hansi@gmail.com", "anniqgmx.at", LocalDate.of(6666,6,6));

        Mockito.when(this.friendsDao.getById(0L)).thenReturn(defaultFriends);
        Mockito.when(this.friendsDao.save(f)).thenReturn(f);

        Friends updated = this.businessLogicFriends.update(0L,f);

        Assertions.assertEquals(f,updated);

    }
    @Test
    public void deleteTest() throws InvalidDataException {
//        Mockito.when(this.friendsDao.save(defaultFriends)).thenReturn(defaultFriends);
//        Mockito.when(this.friendsDao.findAll()).thenReturn()
//        Friends f = this.businessLogicFriends.saveFriendship(defaultFriends);

    }


}
