package aau.at.friendsifyfrontendservice.serviceTests;

import aau.at.friendsifyfrontendservice.models.Person;
import aau.at.friendsifyfrontendservice.services.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import java.time.LocalDate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(PersonService.class)
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    private Person samplePerson;
    private Person[] allPersons;
    private String email;
    private Long id_p;


    @Value("${endpoint.person}")
    private String personServiceEndpoint;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        this.samplePerson = new Person(0L, "Max", "Mustermann", "max@mustermann.de", LocalDate.now(), "Q1234", "Klagenfurt", "password_hash");
        this.allPersons = new Person[1];
        this.allPersons[0] = samplePerson;
        this.email = "max@mustermann.de";
        this.id_p = 0L;
    }

    @AfterEach
    public void tearDown() {
        this.samplePerson = null;
        this.allPersons = null;
        this.email = null;
        this.id_p = null;
        this.server = null;
    }

    @Test
    public void getAll() throws JsonProcessingException {
        String allPersons = objectMapper.writeValueAsString(this.allPersons);
        this.server.expect(requestTo(personServiceEndpoint)).andRespond(withSuccess(allPersons, MediaType.APPLICATION_JSON));

        Person[] allPersonsFound = this.personService.getPersons();
        Assertions.assertEquals(this.allPersons[0].getId_p(), allPersonsFound[0].getId_p());
    }

    @Test
    public void getByEmail() throws JsonProcessingException {
        String person = objectMapper.writeValueAsString(this.samplePerson);
        this.server.expect(requestTo(personServiceEndpoint+"?email=" + this.email)).andRespond(withSuccess(person, MediaType.APPLICATION_JSON));

        Person personFound = this.personService.getPersonByEMail(this.email);
        Assertions.assertEquals(this.samplePerson.getId_p(), personFound.getId_p());
    }

    @Test
    public void getByID() throws JsonProcessingException {
        String person = objectMapper.writeValueAsString(this.samplePerson);
        this.server.expect(requestTo(personServiceEndpoint+"?id=" + this.id_p)).andRespond(withSuccess(person, MediaType.APPLICATION_JSON));

        Person personFound = this.personService.getPersonById(this.id_p);
        Assertions.assertEquals(this.samplePerson.getId_p(), personFound.getId_p());
    }

    @Test
    public void addPerson() {
        this.server.expect(requestTo(personServiceEndpoint)).andExpect(method(HttpMethod.POST)).andRespond(withSuccess());
        this.personService.addPerson(this.samplePerson);
    }

}
