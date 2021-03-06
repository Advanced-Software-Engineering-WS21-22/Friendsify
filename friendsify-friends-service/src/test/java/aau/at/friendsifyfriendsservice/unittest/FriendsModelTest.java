package aau.at.friendsifyfriendsservice.unittest;

import aau.at.friendsifyfriendsservice.model.Friends;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

class FriendsModelTest {
    private Friends friends1;
    private Friends friends1_2;
    private Friends friends2;

    @BeforeEach
    public void setUp(){
        friends1 = new Friends(0L,false,"hans.m@gmail.com","anna@mail.com", LocalDate.of(2021,4,12));
        friends1_2 = new Friends(0L,false,"hans.m@gmail.com","anna@mail.com", LocalDate.of(2021,4,12));
        friends2 = new Friends(3L,true,"anna@gmail.com","hans.m@gmail.com",LocalDate.of(2019,8,19));
    }
    @AfterEach
    public void shutDown(){
        friends1 = null;
        friends1_2 = null;
        friends2 = null;
    }

    //TODO: Add Tests with not allowed values, like null values, blank email ...

    @Test
    void getIDTest_1(){
        Assertions.assertEquals(0L,friends1.getId_friend());
    }
    @Test
    void getIDTest_2(){
        Assertions.assertEquals(3L, friends2.getId_friend());
    }
    @Test
    void setTimeOutTest_1(){
        friends1.setIs_timed_out(true);
        Assertions.assertTrue(friends1.isIs_timed_out());
    }
    @Test
    void setTimeOutTest_2(){
        friends2.setIs_timed_out(false);
        Assertions.assertFalse(friends2.isIs_timed_out());
    }
    @Test
    void getTimeOutTest_1(){
        Assertions.assertFalse(friends1.isIs_timed_out());
    }
    @Test
    void getTimeOutTest_2(){
        Assertions.assertTrue(friends2.isIs_timed_out());
    }
    @Test
    void setEmailInitiatorTest_1(){
        friends1.setEmail_p_initiator("test@gmail.com");
        Assertions.assertEquals("test@gmail.com",friends1.getEmail_p_initiator());
    }
    @Test
    void setEmailInitiatorTest_2(){
        friends2.setEmail_p_initiator("testMax@gmx.com");
        Assertions.assertEquals("testMax@gmx.com",friends2.getEmail_p_initiator());
    }
    @Test
    void getEmailInitiatorTest_1(){
        Assertions.assertEquals("hans.m@gmail.com",friends1.getEmail_p_initiator());
    }
    @Test
    void getEmailInitiatorTest_2(){
        Assertions.assertEquals("anna@gmail.com",friends2.getEmail_p_initiator());
    }
    @Test
    void setEmailFriendTest_1(){
        Assertions.assertNotEquals("peter.m??ller@gmail.com",friends1.getEmail_p_friend());
        friends1.setEmail_p_friend("peter.m??ller@gmail.com");
        Assertions.assertEquals("peter.m??ller@gmail.com",friends1.getEmail_p_friend());
    }
    @Test
    void setEmailFriendTest_2(){
        Assertions.assertNotEquals("testMax@gmx.com",friends2.getEmail_p_friend());
        friends2.setEmail_p_friend("testMax@gmx.com");
        Assertions.assertEquals("testMax@gmx.com",friends2.getEmail_p_friend());
    }
    @Test
    void getEmailFriendTest_1(){
        Assertions.assertNotEquals("testMax@gmx.com",friends1.getEmail_p_friend());
        Assertions.assertEquals("anna@mail.com", friends1.getEmail_p_friend());
    }
    @Test
    void getEmailFriendTest_2(){
        Assertions.assertNotEquals("testMax@gmx.com",friends2.getEmail_p_friend());
        Assertions.assertEquals("hans.m@gmail.com", friends2.getEmail_p_friend());
    }
    @Test
    void setFsStartDateTest_1(){
        LocalDate date1 = LocalDate.of(9999,9,9);
        Assertions.assertNotEquals(date1, friends1.getFs_start_date());
        friends1.setFs_start_date(date1);
        Assertions.assertEquals(date1,friends1.getFs_start_date());
    }
    @Test
    void setFsStartDateTest_2(){
        LocalDate date2 = LocalDate.of(2000,1,1);
        Assertions.assertNotEquals(date2, friends2.getFs_start_date());
        friends2.setFs_start_date(date2);
        Assertions.assertEquals(date2,friends2.getFs_start_date());
    }
    @Test
    void getFsStartDateTest_1(){
        Assertions.assertEquals(LocalDate.of(2021,4,12),friends1.getFs_start_date());
    }
    @Test
    void getFsStartDateTest_2(){
        Assertions.assertEquals(LocalDate.of(2019,8,19),friends2.getFs_start_date());
    }
    @Test
    void updateFromDtoTest(){
        friends1.updateFromDto(friends2);
        Assertions.assertEquals(friends1, friends2);
    }
    @Test
    void toStringTest_1(){
        String f1 = "Friends{" +
                "email_p_initiator='" + "hans.m@gmail.com" + '\'' +
                ", email_p_friend='" + "anna@mail.com" + '\'' +
                ", fs_start_date='" + LocalDate.of(2021,4,12) + '\'' +
                ", is_timed_out='" +false  + '\'' +
                '}';

        Assertions.assertEquals(f1, friends1.toString());
    }
    @Test
    void toStringTest_2(){
        String f2 = "Friends{" +
                "email_p_initiator='" + "anna@gmail.com" + '\'' +
                ", email_p_friend='" + "hans.m@gmail.com" + '\'' +
                ", fs_start_date='" + LocalDate.of(2019,8,19) + '\'' +
                ", is_timed_out='" +true  + '\'' +
                '}';

        Assertions.assertEquals(f2, friends2.toString());
    }

    @Test
    void equalTest(){
        Assertions.assertEquals(friends1, friends1_2);
    }
    @Test
    void notEqualTest(){
        Assertions.assertNotEquals(friends1, friends2);
    }
    @Test
    void notEqualInstanceOfFriends(){
        Assertions.assertNotEquals(friends1, String.class);
    }
    @Test
    void notEqualEmailInitiator(){
        friends1_2.setEmail_p_initiator("testqgmail@test.at");
        Assertions.assertNotEquals(friends1, friends1_2);
    }
    @Test
    void notEqualEmailFriend(){
        friends1_2.setEmail_p_friend("testqgmail@test.at");
        Assertions.assertNotEquals(friends1, friends1_2);
    }
    @Test
    void equalsHashCode(){
        Assertions.assertEquals(friends1.hashCode(),friends1_2.hashCode());
    }
    @Test
    void notEqualsHashCode(){
        Assertions.assertNotEquals(friends1.hashCode(), friends2.hashCode());
    }

}
