package aau.at.friendsifyfrontendservice.contollerTests;
import aau.at.friendsifyfrontendservice.authentication.FriendsifyUser;
import aau.at.friendsifyfrontendservice.controllers.FriendsController;
import aau.at.friendsifyfrontendservice.inputs.FriendsInput;
import aau.at.friendsifyfrontendservice.models.Email;
import aau.at.friendsifyfrontendservice.models.Friends;
import aau.at.friendsifyfrontendservice.models.Person;
import aau.at.friendsifyfrontendservice.models.Recommendation;
import aau.at.friendsifyfrontendservice.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpServerErrorException;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FriendsController.class)
@AutoConfigureMockMvc
public class FriendsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FriendsToPersonService friendsToPersonService;

    @MockBean
    private FindFriendsService findFriendsService;

    @MockBean
    private FriendsService friendsServiceMock;

    @MockBean
    private EmailService emailService;

    @MockBean
    private UserDetailsService userDetailsServiceMock;

    @MockBean
    private RecommendationService recommendationService;

    @MockBean
    private PersonService personService;

    @MockBean
    private JokeService jokeService;

    @MockBean
    private AnniversaryService anniversaryService;

    private Person user;
    private List authorities;

    @BeforeEach
    public void setUp() {
        this.user = new Person(0L, "Max", "Mustermann", "max@mustermann.de", LocalDate.now(), "Q1234", "Klagenfurt", "password_hash");
        this.authorities = new ArrayList();
        this.authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Test
    public void testGetFriends() throws Exception {
        Friends[] active = new Friends[0];
        Friends[] passive = new Friends[0];

        when(this.friendsServiceMock.getFriendsByInitiator("max@mustermann.de")).thenReturn(active);
        when(this.friendsServiceMock.getFriendsByReceiver("max@mustermann.de")).thenReturn(passive);

        this.mockMvc.perform(get("/friends")
                        .with(user(new FriendsifyUser(this.user, true, false, false, false, this.authorities)))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFriendsServerError() throws Exception {
        when(this.friendsServiceMock.getFriendsByInitiator("max@mustermann.de")).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        this.mockMvc.perform(get("/friends")
                        .with(user(new FriendsifyUser(this.user, true, false, false, false, this.authorities)))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testNewFriends() throws Exception {
        Person[] selectables = new Person[0];
        when(this.findFriendsService.findSelectablePersons("max@mustermann.de")).thenReturn(selectables);
        when(this.recommendationService.getRecommendationForPerson(0L)).thenReturn(new Recommendation());

        this.mockMvc.perform(get("/friends/new")
                        .with(user(new FriendsifyUser(this.user, true, false, false, false, this.authorities)))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testNewFriendsServerError() throws Exception {
        when(this.findFriendsService.findSelectablePersons("max@mustermann.de")).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        this.mockMvc.perform(get("/friends/new")
                        .with(user(new FriendsifyUser(this.user, true, false, false, false, this.authorities)))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddFriends() throws Exception {
        FriendsInput input = new FriendsInput("max@mustermann.de", "anna@mustermann.de", false);

        this.mockMvc.perform(post("/friends/new")
                        .with(user(new FriendsifyUser(this.user, true, false, false, false, this.authorities)))
                        .with(csrf())
                        .flashAttr("friendsForm", input)
                        .contentType("application/json"))
                .andExpect(redirectedUrl("/friendsify/friends"));
    }

    @Test
    public void testGetSendMail() throws Exception {

        this.mockMvc.perform(get("/friends/sendMail/max@mustermann.de/anna@mustermann.de")
                        .with(user(new FriendsifyUser(this.user, true, false, false, false, this.authorities)))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSendMail() throws Exception {
        Email email = new Email("max@mustermann.de", "anna@mustermann.de", "subject", "text");
        this.mockMvc.perform(post("/friends/sendMail")
                        .with(csrf())
                        .with(user(new FriendsifyUser(this.user, true, false, false, false, this.authorities)))
                        .contentType("application/json"))
                .andExpect(redirectedUrl("/friendsify/friends"));
    }

    @Test
    public void testSendJoke() throws Exception {
        Person[] allPersons = new Person[2];
        allPersons[0] = new Person(0L, "Max", "Mustermann", "max@mustermann.de", LocalDate.now(), "Q1234", "Klagenfurt", "password_hash");
        allPersons[1] = new Person(1L, "Anna", "Mustermann", "anna@mustermann.de", LocalDate.now(), "Q1234", "Klagenfurt", "password_hash");

        Mockito.when(this.personService.getPersons()).thenReturn(allPersons);

        this.mockMvc.perform(post("/friends/sendJoke/max@mustermann.de/anna@mustermann.de")
                        .with(csrf())
                        .with(user(new FriendsifyUser(this.user, true, false, false, false, this.authorities)))
                        .contentType("application/json"))
                .andExpect(redirectedUrl("/friendsify/friends"));

    }

    @Test
    public void testFriendsDetails() throws Exception {
        Friends friends = new Friends(0L, "max@mustermann.de", "john.doe@email.com", LocalDate.now(), false);
        Mockito.when(this.friendsServiceMock.getFriendsByID(0L)).thenReturn(friends);
        Mockito.when(this.anniversaryService.getAnniversary(friends.getEmail_p_initiator(), friends.getEmail_p_friend())).thenReturn("Days until anniversary: 245");

        this.mockMvc.perform(get("/friends/0")
                .with(csrf())
                .with(user(new FriendsifyUser(this.user, true, false, false, false, this.authorities)))
                .contentType("application/json"))
                .andExpect(status().isOk());
    }













}
