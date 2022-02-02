package aau.at.friendsifypersonservice;

import aau.at.friendsifypersonservice.exceptions.PersonNotFoundException;
import aau.at.friendsifypersonservice.models.Person;
import aau.at.friendsifypersonservice.repositories.PersonDao;
import org.junit.jupiter.api.BeforeAll;
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
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PersonServiceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonDao personDao;

    Person defaultPerson1;
    Person defaultPerson1_2;
    Person defaultPerson2;
    Person defaultPerson3;

    static final Long DEFAULT_ID = 1L;
    static final Long DEFAULT_ID_NOT_AVAILABLE = 6L;
    static final String DEFAULT_EMAIL = "max@mustermann.de";
    static final String DEFAULT_EMAIL_NOT_AVAILABLE = "clara@nussknacker.de";
    static final String DEFAULT_EXCEPTION_ID = "Person not found by id: " + DEFAULT_ID_NOT_AVAILABLE;
    static final String DEFAULT_EXCEPTION_EMAIL = "Person not found by email: " + DEFAULT_EMAIL_NOT_AVAILABLE;

    @BeforeEach
    void init() {
        defaultPerson1 = new Person(1L, "Max", "Mustermann", LocalDate.of(2000, 1, 1), "max@mustermann.de", "cGFzc3dvcmQ=", "Q483522", "Villach");
        defaultPerson1_2 = new Person(1L, "Anna", "Mustermann", LocalDate.of(2001, 1, 1), "anna@mustermann.de", "cGFzc3dvcmQ", "Q483522", "Villach");
        defaultPerson2 = new Person(2L, "John", "Doe", LocalDate.of(1990, 6, 6), "john.doe@email.com", "cGFzc3dvcmQ", "Q41753", "Klagenfurt");
        defaultPerson3 = new Person(3L, "Hans", "Mueller", LocalDate.of(1994, 8, 18), "hans.m@gmail.com", "cGFzc3dvcmQ", "Q660687", "Velden am Woerthersee");
        personDao.save(defaultPerson1);
        personDao.save(defaultPerson2);
        personDao.save(defaultPerson3);
    }

    @Test
    void givenIDFindAll() throws Exception {

        String response="["+defaultPerson1.toString().replaceAll("[\\n\\t ]", "")+","+
                defaultPerson2.toString().replaceAll("[\\n\\t ]", "")+","+
                defaultPerson3.toString().replaceAll("[\\n\\t ]", "")+"]";

        MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/persons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(response,result.getResponse().getContentAsString().replaceAll("\"","").replaceAll(":","=").replaceAll("[\\n\\t ]", ""));
    }

    @Test
    void givenIDFindPerson() throws Exception {

        MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/persons?id="+DEFAULT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(defaultPerson1.toString().replaceAll("[\\n\\t ]", ""),result.getResponse().getContentAsString().replaceAll("\"","").replaceAll(":","=").replaceAll("[\\n\\t ]", ""));
    }

    @Test
    void givenEmailFindPerson() throws Exception {

        MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/persons?email="+DEFAULT_EMAIL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(defaultPerson1.toString().replaceAll("[\\n\\t ]", ""),result.getResponse().getContentAsString().replaceAll("\"","").replaceAll(":","=").replaceAll("[\\n\\t ]", ""));
    }


    @Test
    void createPerson() {

        String json = "{\n" +
                "\"id_p\": 1,\n" +
                "\"first_name\": \"Maxime\",\n" +
                "\"last_name\": \"Mustermann\",\n" +
                "\"birthday\": \"2000-01-01\",\n" +
                "\"email\": \"maxime@mustermann.de\",\n" +
                "\"password\": \"cGFzc3dvcmQ=\",\n" +
                "\"id_geoDB\": \"Q483522\",\n" +
                "\"city\": \"Villach\"\n" +
                "}";

        try {
            MvcResult result = mvc.perform(MockMvcRequestBuilders
                    .post("/persons")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8"))
                    .andExpect(status().isOk())
                    .andReturn();
            assertNotNull(result);
            assertEquals(json.replaceAll("[\\n\\t ]", ""), result.getResponse().getContentAsString());
        } catch (Exception e) {
            fail("No exception should have been thrown!");
        }

    }

    @Test
    void updatePersonID() {

        String json = "{\n" +
                "\"id_p\": 1,\n" +
                "\"first_name\": \"Anna\",\n" +
                "\"last_name\": \"Mustermann\",\n" +
                "\"birthday\": \"2000-01-01\",\n" +
                "\"email\": \"anna@mustermann.de\",\n" +
                "\"password\": \"cGFzc3dvcmQ\",\n" +
                "\"id_geoDB\": \"Q483522\",\n" +
                "\"city\": \"Villach\"\n" +
                "}";

        try {
            MvcResult result = mvc.perform(MockMvcRequestBuilders
                    .put("/persons?id="+DEFAULT_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8"))
                    .andExpect(status().isOk())
                    .andReturn();
            assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        } catch (Exception e) {
            fail("No exception should have been thrown!");
        }

    }

    @Test
    void updatePersonEMAIL() {

        String json = "{\n" +
                "\"id_p\": 1,\n" +
                "\"first_name\": \"Anna\",\n" +
                "\"last_name\": \"Mustermann\",\n" +
                "\"birthday\": \"2000-01-01\",\n" +
                "\"email\": \"anna@mustermann.de\",\n" +
                "\"password\": \"cGFzc3dvcmQ\",\n" +
                "\"id_geoDB\": \"Q483522\",\n" +
                "\"city\": \"Villach\"\n" +
                "}";

        try {
            MvcResult result = mvc.perform(MockMvcRequestBuilders
                    .put("/persons?email="+DEFAULT_EMAIL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8"))
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            fail("No exception should have been thrown!");
        }
    }

    @Test
    void deletePersonID() {
        try {
            mvc.perform(MockMvcRequestBuilders
                    .delete("/persons?id="+DEFAULT_ID))
                    .andExpect(result -> equals("\"deleted\":true"))
                    .andExpect(status().isOk());
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }catch (Exception ex){
            fail("No exception should have been thrown!");
        }
    }

    @Test
    void deletePersonEMAIL() {
        try {
            mvc.perform(MockMvcRequestBuilders
                    .delete("/persons?email="+DEFAULT_EMAIL))
                    .andExpect(result -> equals("\"deleted\":true"))
                    .andExpect(status().isOk());
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }catch (Exception ex){
            fail("No exception should have been thrown!");
        }
    }

}
