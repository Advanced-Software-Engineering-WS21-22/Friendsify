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
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BusinessLogicFriendsTest {
    private Friends defaultFriends;
    private Friends updateFriends;
    private List<Friends> friends = new ArrayList<>();
    private final String default_email_initiator = "hans.m@gmail.com";
    private final String default_email_friend = "anna@gmx.at";
    private final boolean default_is_timed_out = false;
    private final LocalDate default_fs_start_date = LocalDate.of(2022,1,26);

    @Mock
    private FriendsDao friendsDao;

    @InjectMocks
    private BusinessLogicFriends businessLogicFriends;

    @BeforeEach
    public void setup(){
        defaultFriends = new Friends(0L, default_is_timed_out,default_email_initiator,default_email_friend, default_fs_start_date);
        updateFriends = new Friends(0L,true, "hansi@gmail.com", "anni@gmx.at", LocalDate.of(6666,6,6));
        friends.add(defaultFriends);

    }
    @AfterEach
    public void tearDown(){
        defaultFriends = null;
        friends = null;
    }

    private List<Friends> indicateGetAllFriendships(){
        Mockito.when(this.friendsDao.findAll()).thenReturn(friends);

        return this.businessLogicFriends.getAllFriendship();
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
        Assertions.assertEquals(friends, indicateGetAllFriendships());
    }

    private List<Friends> indicateGetByEmailInitiator() throws ResourceNotFoundException {
        Mockito.when(this.friendsDao.findAll()).thenReturn(friends);

        return this.businessLogicFriends.getByEmailInitiator(default_email_initiator);
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
        Assertions.assertEquals(friends,indicateGetByEmailInitiator());

    }
    private List<Friends> indicateGetByEmailFriend() throws ResourceNotFoundException {
        Mockito.when(this.friendsDao.findAll()).thenReturn(friends);

        return this.businessLogicFriends.getByEmailFriend(default_email_friend);
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
        Assertions.assertEquals(friends,indicateGetByEmailFriend());
    }

    @Test
    public void getIDTest() throws ResourceNotFoundException {
        Mockito.when(this.friendsDao.findAll()).thenReturn(friends);

        Long id = this.businessLogicFriends.getID(default_email_initiator,default_email_friend);

        Assertions.assertEquals(0L,id);
    }

    @Test
    public void saveFriendshipTest() throws InvalidDataException {
        Mockito.when(this.friendsDao.save(defaultFriends)).thenReturn(defaultFriends);

        Friends f = this.businessLogicFriends.saveFriendship(defaultFriends);

        Assertions.assertEquals(f,defaultFriends);
        Assertions.assertEquals(f.getId_friend(),defaultFriends.getId_friend());
        Assertions.assertEquals(f.getEmail_p_initiator(),defaultFriends.getEmail_p_initiator());
        Assertions.assertEquals(f.getEmail_p_friend(),defaultFriends.getEmail_p_friend());
        Assertions.assertEquals(f.isIs_timed_out(),defaultFriends.isIs_timed_out());
        Assertions.assertEquals(f.getFs_start_date(),defaultFriends.getFs_start_date());
    }

    @Test
    public void updateTest() throws InvalidDataException, ResourceNotFoundException {
        when(this.friendsDao.findById(0L)).thenReturn(java.util.Optional.ofNullable(defaultFriends));
        when(this.friendsDao.save(updateFriends)).thenReturn(updateFriends);

        Friends updated = this.businessLogicFriends.update(0L,updateFriends);

        Assertions.assertEquals(updated, defaultFriends);

        Assertions.assertEquals(updateFriends,updated);
        Assertions.assertEquals(updateFriends.getId_friend(),updated.getId_friend());
        Assertions.assertEquals(updateFriends.isIs_timed_out(),updated.isIs_timed_out());
        Assertions.assertEquals(updateFriends.getEmail_p_initiator(),updated.getEmail_p_initiator());
        Assertions.assertEquals(updateFriends.getEmail_p_friend(),updated.getEmail_p_friend());
        Assertions.assertEquals(updateFriends.getFs_start_date(),updated.getFs_start_date());
    }
    @Test
    public void deleteTest() throws ResourceNotFoundException {
        Mockito.when(this.friendsDao.findById(0L)).thenReturn(java.util.Optional.ofNullable(defaultFriends));
        Mockito.doAnswer(invocationOnMock -> {
            friends.remove(defaultFriends);
            return friends;
        }).when(this.friendsDao).delete(defaultFriends);

        Mockito.when(this.friendsDao.findAll()).thenReturn(friends);
        List<Friends> returned = this.businessLogicFriends.getAllFriendship();

        Assertions.assertEquals(1,returned.size());
        this.businessLogicFriends.delete(0L);
        Assertions.assertEquals(0,returned.size());

    }


}
