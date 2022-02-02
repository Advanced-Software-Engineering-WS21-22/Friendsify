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

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BusinessLogicFriendsTest {
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
    private final String default_exception_id = "Friendship not found for id: " + default_notValid_ID;
    private final String default_exception_emailInitiator = "No Friendships with this initiator email found: "+ default_notValid_emailInitiator;
    private final String default_exception_emailFriend = "No Friendships with this initiator email found: "+ default_notValid_emailFriend;
    private final String default_exception_getID = "Friendship with email_initiator = "+default_notValid_emailInitiator+" and email_friend = "+default_notValid_emailFriend+" was not found.";


    @Mock
    private FriendsDao friendsDao;

    @InjectMocks
    private BusinessLogicFriends businessLogicFriends;

    @BeforeEach
    public void setup(){
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
    public void tearDown(){
        defaultFriends = null;
        defaultFriends_2 = null;
        defaultFriends_3 = null;
        friendshipToMyself = null;
        updateFriends = null;
        friends = null;
    }

    @Test
    public void getAllFriendshipsTest(){
        when(this.friendsDao.findAll()).thenReturn(this.friends);
        assertThat(this.businessLogicFriends.getAllFriendship()).isNotNull().isEqualTo(this.friends).size().isEqualTo(this.friends.size());
        verify(this.friendsDao,times(1)).findAll();
    }

    @Test
    public void getByIDTest() throws ResourceNotFoundException {
        when(this.friendsDao.findById(defaultFriends.getId_friend())).thenReturn(java.util.Optional.ofNullable(defaultFriends));
        try {
            assertThat(this.businessLogicFriends.getById(defaultFriends.getId_friend())).isNotNull().isEqualTo(defaultFriends);
            verify(this.friendsDao,times(1)).findById(defaultFriends.getId_friend());
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No Friendship found with id: "+defaultFriends.getId_friend());
        }
    }
    @Test
    public void getByIDTestFail(){
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()-> this.businessLogicFriends.getById(default_notValid_ID));
        assertEquals(default_exception_id,exception.getMessage());
        verify(this.friendsDao,times(1)).findById(default_notValid_ID);
    }
    @Test
    public void getByEmailInitiatorTest() throws ResourceNotFoundException {
        when(this.friendsDao.findAll()).thenReturn(friends);
        try {
            assertThat(this.businessLogicFriends.getByEmailInitiator(default_email_initiator)).isNotNull().hasSize(1);
            verify(this.friendsDao,times(1)).findAll();
            assertThat(this.businessLogicFriends.getByEmailInitiator(default_email_initiator).get(0)).isNotNull().isEqualTo(defaultFriends);
            verify(this.friendsDao,times(2)).findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No Friendships with this initiator email found: "+ default_email_initiator);
        }
    }
    @Test
    public void getByEmailInitiatorTest_Fail(){
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> this.businessLogicFriends.getByEmailInitiator(default_notValid_emailInitiator));
        assertEquals(default_exception_emailInitiator,exception.getMessage());
        verify(this.friendsDao,times(1)).findAll();
    }
    @Test
    public void getByEmailFriendTest() throws ResourceNotFoundException {
        when(this.friendsDao.findAll()).thenReturn(friends);
        try {
            assertThat(this.businessLogicFriends.getByEmailFriend(default_email_friend)).isNotNull().hasSize(1);
            verify(this.friendsDao,times(1)).findAll();
            assertThat(this.businessLogicFriends.getByEmailFriend(default_email_friend).get(0)).isNotNull().isEqualTo(defaultFriends);
            verify(this.friendsDao,times(2)).findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No Friendships with this friend email found: "+ default_email_friend);
        }
    }
    @Test
    public void getByEmailFriendTest_Fail(){
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> this.businessLogicFriends.getByEmailInitiator(default_notValid_emailFriend));
        assertEquals(default_exception_emailFriend,exception.getMessage());
        verify(this.friendsDao,times(1)).findAll();
    }
    @Test
    public void getIDTest() throws ResourceNotFoundException {
        Mockito.when(this.friendsDao.findAll()).thenReturn(friends);
        try {
            assertThat(this.businessLogicFriends.getID(default_email_initiator,default_email_friend)).isNotNull().isEqualTo(defaultFriends.getId_friend());
            verify(this.friendsDao,times(1)).findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Friendship with email_initiator = "+default_email_initiator+" and email_friend = "+default_email_friend+" was not found.");
        }
    }
    @Test
    public void getIDTest_Fail(){
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> this.businessLogicFriends.getID(default_notValid_emailInitiator,default_notValid_emailFriend));
        assertEquals(default_exception_getID,exception.getMessage());
        verify(this.friendsDao,times(1)).findAll();
    }

    @Test
    public void saveFriendshipTest() throws InvalidDataException {
        when(this.friendsDao.save(defaultFriends)).thenReturn(defaultFriends);
        assertThat(this.businessLogicFriends.saveFriendship(defaultFriends)).isNotNull().isEqualTo(defaultFriends);
        verify(this.friendsDao,times(1)).save(defaultFriends);
    }
    @Test
    public void saveFriendshipTest_Fail_duplicate(){
        when(this.friendsDao.findAll()).thenReturn(friends);
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> this.businessLogicFriends.saveFriendship(defaultFriends));
        assertEquals("Friendship is already in DB.",exception.getMessage());
        verify(this.friendsDao,times(1)).findAll();
        verify(this.friendsDao,times(0)).save(defaultFriends);

    }
    @Test
    public void saveFriendshipTest_Fail_toItself(){
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> this.businessLogicFriends.saveFriendship(friendshipToMyself));
        assertEquals("Friendship to itself is not allowed.", exception.getMessage());
        verify(this.friendsDao,times(0)).save(friendshipToMyself);
    }
    @Test
    public void updateTest() throws InvalidDataException, ResourceNotFoundException {
        when(this.friendsDao.findById(defaultFriends.getId_friend())).thenReturn(java.util.Optional.ofNullable(defaultFriends));
        when(this.friendsDao.save(updateFriends)).thenReturn(updateFriends);

        assertThat(this.businessLogicFriends.update(defaultFriends.getId_friend(),updateFriends)).isNotNull().isEqualTo(defaultFriends);
        verify(this.friendsDao,times(1)).findById(defaultFriends.getId_friend());
        verify(this.friendsDao,times(1)).save(updateFriends);
    }
    @Test
    public void updateTest_Fail_duplicate(){
        when(this.friendsDao.findAll()).thenReturn(friends);
        when(this.friendsDao.findById(defaultFriends_2.getId_friend())).thenReturn(java.util.Optional.ofNullable(defaultFriends_2));
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> this.businessLogicFriends.update(defaultFriends_2.getId_friend(),defaultFriends));

        assertEquals("Friendship is already in DB.",exception.getMessage());
        verify(this.friendsDao,times(1)).findById(defaultFriends_2.getId_friend());
        verify(this.friendsDao,times(0)).save(defaultFriends);

    }
    @Test
    public void updateTest_Fail_toItself(){
        when(this.friendsDao.findById(defaultFriends.getId_friend())).thenReturn(java.util.Optional.ofNullable(defaultFriends));

        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> this.businessLogicFriends.update(defaultFriends.getId_friend(),friendshipToMyself));
        assertEquals("Friendship to itself is not allowed.", exception.getMessage());
        verify(this.friendsDao,times(0)).save(friendshipToMyself);
        verify(this.friendsDao,times(1)).findById(defaultFriends.getId_friend());
    }



    @Test
    public void deleteTest() throws ResourceNotFoundException {
        when(this.friendsDao.findById(0L)).thenReturn(java.util.Optional.ofNullable(defaultFriends));
        int friendsListSize = this.friends.size();
        doAnswer(invocationOnMock -> {
            friends.remove(defaultFriends);
            return friends;
        }).when(this.friendsDao).delete(defaultFriends);


        try {
            Assertions.assertEquals(friendsListSize,friends.size());
            this.businessLogicFriends.delete(defaultFriends.getId_friend());
            Assertions.assertEquals(friendsListSize-1,friends.size());

            verify(this.friendsDao,times(1)).findById(defaultFriends.getId_friend());
            verify(this.friendsDao,times(1)).delete(defaultFriends);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No Friendship found with id: "+defaultFriends.getId_friend());
        }
    }
}
