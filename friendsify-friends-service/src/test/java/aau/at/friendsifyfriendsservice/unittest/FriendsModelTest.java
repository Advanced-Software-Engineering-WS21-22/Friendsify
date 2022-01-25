package aau.at.friendsifyfriendsservice.unittest;

import aau.at.friendsifyfriendsservice.model.Friends;
import org.junit.jupiter.api.*;

import java.sql.Date;

public class FriendsModelTest {
    private static Friends friends1;
    private static Friends friends2;

    @BeforeEach
    public void setUp(){
        friends1 = new Friends(0L,false,"hans.m@gmail.com","anna@mail.com", new Date(2021,4,12));
        friends2 = new Friends(3L,true,"anna@gmail.com","hans.m@gmail.com",new Date(2019,8,19));
    }
    @AfterAll
    public static void shutDown(){
        friends1 = null;
        friends2 = null;
    }

    @Test
    public void getIDTest(){
        Assertions.assertEquals(0L,friends1.getId_friend());
        Assertions.assertEquals(3L, friends2.getId_friend());
    }
    @Test
    public void setIDTest(){
        friends1.setId_friend(7L);
        Assertions.assertEquals(7L,friends1.getId_friend());

        friends2.setId_friend(12L);
        Assertions.assertEquals(12L,friends2.getId_friend());
    }
    @Test
    public void setTimeOutTest(){
        friends1.setIs_timed_out(true);
        Assertions.assertTrue(friends1.isIs_timed_out());

        friends2.setIs_timed_out(false);
        Assertions.assertFalse(friends2.isIs_timed_out());
    }
    @Test
    public void getTimeOutTest(){
        Assertions.assertFalse(friends1.isIs_timed_out());
        Assertions.assertTrue(friends2.isIs_timed_out());
    }
    @Test
    public void setEmailInitiatorTest(){
        friends1.setEmail_p_initiator("test@gmail.com");
        Assertions.assertEquals("test@gmail.com",friends1.getEmail_p_initiator());

        friends2.setEmail_p_initiator("testMax@gmx.com");
        Assertions.assertEquals("testMax@gmx.com",friends2.getEmail_p_initiator());
    }
    @Test
    public void getEmailInitiatorTest(){
        Assertions.assertEquals("hans.m@gmail.com",friends1.getEmail_p_initiator());
        Assertions.assertEquals("anna@gmail.com",friends2.getEmail_p_initiator());
    }
    @Test
    public void setEmailFriendTest(){
        Assertions.assertNotEquals("peter.müller@gmail.com",friends1.getEmail_p_friend());
        friends1.setEmail_p_friend("peter.müller@gmail.com");
        Assertions.assertEquals("peter.müller@gmail.com",friends1.getEmail_p_friend());

        Assertions.assertNotEquals("testMax@gmx.com",friends2.getEmail_p_friend());
        friends2.setEmail_p_friend("testMax@gmx.com");
        Assertions.assertEquals("testMax@gmx.com",friends2.getEmail_p_friend());
    }
    @Test
    public void getEmailFriendTest(){
        Assertions.assertNotEquals("testMax@gmx.com",friends1.getEmail_p_friend());
        Assertions.assertEquals("anna@mail.com", friends1.getEmail_p_friend());
        Assertions.assertNotEquals("testMax@gmx.com",friends2.getEmail_p_friend());
        Assertions.assertEquals("hans.m@gmail.com", friends2.getEmail_p_friend());
    }
    @Test
    public void setFsStartDateTest(){
        Date date1 = new Date(9999,9,9);
        Assertions.assertNotEquals(date1, friends1.getFs_start_date());
        friends1.setFs_start_date(date1);
        Assertions.assertEquals(date1,friends1.getFs_start_date());

        Date date2 = new Date(2000,1,1);
        Assertions.assertNotEquals(date2, friends2.getFs_start_date());
        friends2.setFs_start_date(date2);
        Assertions.assertEquals(date2,friends2.getFs_start_date());
    }
    @Test
    public void getFsStartDateTest(){
        Assertions.assertEquals(new Date(2021,4,12),friends1.getFs_start_date());
        Assertions.assertEquals(new Date(2019,8,19),friends2.getFs_start_date());
    }
    @Test
    public void updateFromDtoTest(){
        Assertions.assertNotEquals(friends2.getId_friend(),friends1.getId_friend());
        Assertions.assertNotEquals(friends2.isIs_timed_out(),friends1.isIs_timed_out());
        Assertions.assertNotEquals(friends2.getEmail_p_initiator(),friends1.getEmail_p_initiator());
        Assertions.assertNotEquals(friends2.getEmail_p_friend(),friends1.getEmail_p_friend());
        Assertions.assertNotEquals(friends2.getFs_start_date(),friends1.getFs_start_date());

        friends1.updateFromDto(friends2);

        Assertions.assertNotEquals(friends2.getId_friend(),friends1.getId_friend());
        Assertions.assertEquals(friends2.isIs_timed_out(),friends1.isIs_timed_out());
        Assertions.assertEquals(friends2.getEmail_p_initiator(),friends1.getEmail_p_initiator());
        Assertions.assertEquals(friends2.getEmail_p_friend(),friends1.getEmail_p_friend());
        Assertions.assertEquals(friends2.getFs_start_date(),friends1.getFs_start_date());
    }
    @Test
    public void toStringTest(){
        String f1 = "Friends{" +
                "id_p_initiator='" + "hans.m@gmail.com" + '\'' +
                ", id_p_friend='" + "anna@mail.com" + '\'' +
                ", fs_start_date='" + new Date(2021,4,12) + '\'' +
                ", timed_out='" +false  + '\'' +
                '}';

        Assertions.assertEquals(f1, friends1.toString());

        String f2 = "Friends{" +
                "id_p_initiator='" + "anna@gmail.com" + '\'' +
                ", id_p_friend='" + "hans.m@gmail.com" + '\'' +
                ", fs_start_date='" + new Date(2019,8,19) + '\'' +
                ", timed_out='" +true  + '\'' +
                '}';

        Assertions.assertEquals(f2, friends2.toString());
    }

}
