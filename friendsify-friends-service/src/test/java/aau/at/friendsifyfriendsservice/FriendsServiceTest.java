package aau.at.friendsifyfriendsservice;

import aau.at.friendsifyfriendsservice.model.Friends;
import aau.at.friendsifyfriendsservice.repositories.FriendsDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FriendsServiceTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private FriendsDao friendsDao;

    private Friends defaultFriends;
    private Friends defaultFriends_2;
    private Friends defaultFriends_3;
    private final String default_email_initiator = "hans.m@gmail.com";
    private final String default_email_friend = "anna@gmx.at";
    private final boolean default_is_timed_out = false;
    private final LocalDate default_fs_start_date = LocalDate.of(2022,1,26);
    private String newDefault_1;
    private String newDefault_2;
    private String newDefault_3;


    @BeforeEach
    public void setUp(){
        defaultFriends = new Friends(1L, default_is_timed_out,default_email_initiator,default_email_friend, default_fs_start_date);
        defaultFriends_2 = new Friends(2L, true,"test@gamil.com","friendTest@aau.at",LocalDate.of(2006,8,5));
        defaultFriends_3 = new Friends(3L, default_is_timed_out, default_email_friend,default_email_initiator,default_fs_start_date);
        friendsDao.save(defaultFriends);
        friendsDao.save(defaultFriends_2);
        friendsDao.save(defaultFriends_3);

        String oldDefault_1 = defaultFriends.toString().replaceAll("[\\n\\t ]", "").replace("Friends","").replaceAll("'","");
        newDefault_1 = oldDefault_1.charAt(0)+"id_friend="+defaultFriends.getId_friend()+","+oldDefault_1.substring(1);

        String oldDefault_2 = defaultFriends_2.toString().replaceAll("[\\n\\t ]", "").replace("Friends","").replaceAll("'","");
        newDefault_2 = oldDefault_2.charAt(0)+"id_friend="+defaultFriends_2.getId_friend()+","+oldDefault_2.substring(1);

        String oldDefault_3 = defaultFriends_3.toString().replaceAll("[\\n\\t ]", "").replace("Friends","").replaceAll("'","");
        newDefault_3 = oldDefault_3.charAt(0)+"id_friend="+defaultFriends_3.getId_friend()+","+oldDefault_3.substring(1);

    }
    @Test
    void findAll() throws Exception {
        String response="["+newDefault_1+","+
                newDefault_2+","+
                newDefault_3+"]";

        MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/friends")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(response,result.getResponse().getContentAsString().replaceAll("\"","").replaceAll(":","=").replaceAll("[\\n\\t ]", ""));
    }
    @Test
    void givenIDFindFriendship() throws Exception {

        MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/friends/"+defaultFriends.getId_friend())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(newDefault_1,result.getResponse().getContentAsString().replaceAll("\"","").replaceAll(":","=").replaceAll("[\\n\\t ]", ""));
    }
    @Test
    void givenEmailInitiatorFindFriendships() throws Exception {

        MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/friends/?email_initiator="+default_email_initiator)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals("["+newDefault_1+"]",result.getResponse().getContentAsString().replaceAll("\"","").replaceAll(":","=").replaceAll("[\\n\\t ]", ""));
    }
    @Test
    void givenEmailFriendFindFriendships() throws Exception {

        MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/friends/?email_friend="+default_email_friend)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals("["+newDefault_1+"]",result.getResponse().getContentAsString().replaceAll("\"","").replaceAll(":","=").replaceAll("[\\n\\t ]", ""));
    }
    @Test
    void givenEmailInitiatorEmailFriendFindID() throws Exception {
        MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/friends/?email_initiator="+default_email_initiator+"&email_friend="+default_email_friend)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(defaultFriends.getId_friend().toString(),result.getResponse().getContentAsString());
    }
    @Test
    void createFriendship() throws Exception {

        String json = "{\n" +
                "\"id_friend\": 4,\n" +
                "\"email_p_initiator\": \"neu.m@gmail.com\",\n" +
                "\"email_p_friend\": \"neu-anna@gmx.at\",\n" +
                "\"fs_start_date\": \"2022-01-26\",\n" +
                "\"is_timed_out\": false \n" +
                "}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/friends")
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
    void updateFriendshipGivenIDandUpdatedFriendship() throws Exception {

        String json = "{\n" +
                "\"id_friend\": 4,\n" +
                "\"email_p_initiator\": \"update@gmail.com\",\n" +
                "\"email_p_friend\": \"update@gmx.at\",\n" +
                "\"fs_start_date\": \"6262-02-06\",\n" +
                "\"is_timed_out\": true \n" +
                "}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .put("/friends/"+defaultFriends.getId_friend())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }
    @Test
    void deleteFriendshipGivenID() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/friends/"+defaultFriends.getId_friend()))
                .andExpect(result -> equals("\"deleted\":true"))
                .andExpect(status().isOk());

    }

}
