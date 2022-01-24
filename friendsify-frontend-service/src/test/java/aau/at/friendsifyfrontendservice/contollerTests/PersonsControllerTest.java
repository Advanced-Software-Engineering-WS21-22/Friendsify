package aau.at.friendsifyfrontendservice.contollerTests;


import aau.at.friendsifyfrontendservice.controllers.PersonsController;
import aau.at.friendsifyfrontendservice.inputs.PersonInput;
import aau.at.friendsifyfrontendservice.models.Person;
import aau.at.friendsifyfrontendservice.services.PersonService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonsController.class)
@AutoConfigureMockMvc
public class PersonsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personServiceMock;

    @MockBean
    private UserDetailsService userDetailsServiceMock;

    @Before
    public void setUp() {

    }

    @Test
    @WithMockUser(username = "max@mustermann.de", password = "password", roles = "USER")
    public void testPersonsRequest() throws Exception {
        Person[] expectedPeople = new Person[0];
        when(personServiceMock.getPersons()).thenReturn(expectedPeople);
        this.mockMvc.perform(get("/persons")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "max@mustermann.de", password = "password", roles = "USER")
    public void testPersonByIDRequest() throws Exception {
        Person person = new Person(0L, "Max", "Mustermann", "max@mustermann.de", LocalDate.now(), "Q1234", "Klagenfurt", "password_hash");
        when(personServiceMock.getPersonById(0L)).thenReturn(person);
        this.mockMvc.perform(get("/persons/{id_p}", "0")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "max@mustermann.de", password = "password", roles = "USER")
    public void testAddPersonWithPositivePasswords() throws Exception {
        PersonInput personInput = new PersonInput(0L, "Max", "Mustermann", "max@mustermann.de", LocalDate.now(), "Q1234", "Klagenfurt", "test", "test");

        this.mockMvc.perform(post("/persons")
                        .flashAttr("personForm", personInput)
                        .contentType("application/json"))
                .andExpect(redirectedUrl("./persons"));
    }

    @Test
    @WithMockUser(username = "max@mustermann.de", password = "password", roles = "USER")
    public void testAddPersonWithNegativePasswords() throws Exception {
        PersonInput personInput = new PersonInput(0L, "Max", "Mustermann", "max@mustermann.de", LocalDate.now(), "Q1234", "Klagenfurt", "same", "notsame");

        this.mockMvc.perform(post("/persons")
                        .flashAttr("personForm", personInput)
                        .contentType("application/json"))
                .andExpect(redirectedUrl("./persons?pwmatcherror"));
    }



}
