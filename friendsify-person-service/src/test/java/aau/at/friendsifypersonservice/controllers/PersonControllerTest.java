package aau.at.friendsifypersonservice.controllers;

import aau.at.friendsifypersonservice.exceptions.PersonNotFoundException;
import aau.at.friendsifypersonservice.models.Person;
import aau.at.friendsifypersonservice.servicelogics.PersonServiceLogic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @MockBean
    private PersonServiceLogic service;

    @Autowired
    private MockMvc mockMvc;

    List<Person> default_people;
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
    void setUp() {
        defaultPerson1 = new Person(1L, "Max", "Mustermann", LocalDate.of(2000, 1, 1), "max@mustermann.de", "cGFzc3dvcmQ=", "Q483522", "Villach");
        defaultPerson1_2 = new Person(1L, "Anna", "Mustermann", LocalDate.of(2001, 1, 1), "anna@mustermann.de", "cGFzc3dvcmQ", "Q483522", "Villach");
        defaultPerson2 = new Person(3L, "John", "Doe", LocalDate.of(1990, 6, 6), "john.doe@email.com", "cGFzc3dvcmQ", "Q41753", "Klagenfurt");
        defaultPerson3 = new Person(4L, "Hans", "Müller", LocalDate.of(1994, 8, 18), "hans.m@gmail.com", "cGFzc3dvcmQ", "Q660687", "Velden am Wörthersee");
        default_people = new ArrayList<>();
        default_people.add(defaultPerson1);
        default_people.add(defaultPerson2);
        default_people.add(defaultPerson3);
    }

    @AfterEach
    void tearDown() {
        default_people = null;
        defaultPerson1 = null;
        defaultPerson1_2 = null;
        defaultPerson2 = null;
        defaultPerson3 = null;
    }

    @Test
    void getAllPersons() {
        when(service.getAllPersons()).thenReturn(default_people);
        int defaultSize = default_people.size();
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/persons")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(defaultSize)))
                    .andExpect(jsonPath("$[0].id_p", is(defaultPerson1.getId_p().intValue())))
                    .andExpect(jsonPath("$[1].id_p", is(defaultPerson2.getId_p().intValue())))
                    .andExpect(jsonPath("$[2].id_p", is(defaultPerson3.getId_p().intValue())));
        } catch (Exception e) {
            fail("Should have found all persons!");
        }

        verify(service, times(1)).getAllPersons();
    }

    @Test
    void getPersonByID() {
        try {
            when(service.getPersonByID(DEFAULT_ID)).thenReturn(defaultPerson1);
            mockMvc.perform(MockMvcRequestBuilders.get("/persons?id=" + DEFAULT_ID)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id_p", is(defaultPerson1.getId_p().intValue())));
            verify(service, times(1)).getPersonByID(DEFAULT_ID);
        } catch (Exception e) {
            fail("Should have found person!");
        }
    }

    @Test
    void getPersonByIDFail() {
        try {
            when(service.getPersonByID(DEFAULT_ID_NOT_AVAILABLE)).thenThrow(new PersonNotFoundException("Person not found by id: " + DEFAULT_ID_NOT_AVAILABLE));
            mockMvc.perform(MockMvcRequestBuilders.get("/persons?id=" + DEFAULT_ID_NOT_AVAILABLE)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
            verify(service, times(1)).getPersonByID(DEFAULT_ID_NOT_AVAILABLE);
        } catch (Exception e) {
            fail("Not Found should have been sent!");
        }
    }

    @Test
    void getPersonByEMAIL() {
        try {
            when(service.getPersonByEmail(DEFAULT_EMAIL)).thenReturn(defaultPerson1);
            mockMvc.perform(MockMvcRequestBuilders.get("/persons?email=" + DEFAULT_EMAIL)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.email", is(defaultPerson1.getEmail())));
            verify(service, times(1)).getPersonByEmail(DEFAULT_EMAIL);
        } catch (Exception e) {
            fail("Should have found person!");
        }
    }

    @Test
    void getPersonByEMAILFail() {
        try {
            when(service.getPersonByEmail(DEFAULT_EMAIL_NOT_AVAILABLE)).thenThrow(new PersonNotFoundException("Person not found by email: " + DEFAULT_EMAIL_NOT_AVAILABLE));
            mockMvc.perform(MockMvcRequestBuilders.get("/persons?email=" + DEFAULT_EMAIL_NOT_AVAILABLE)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
            verify(service, times(1)).getPersonByEmail(DEFAULT_EMAIL_NOT_AVAILABLE);
        } catch (Exception e) {
            fail("Not Found should have been sent!");
        }
    }

    @Test
    void createPerson() {
        when(service.createPerson(defaultPerson1)).thenReturn(defaultPerson1);

        String json = "{\n" +
                "\"id_p\": 1,\n" +
                "\"first_name\": \"Max\",\n" +
                "\"last_name\": \"Mustermann\",\n" +
                "\"birthday\": \"2000-01-01\",\n" +
                "\"email\": \"max@mustermann.de\",\n" +
                "\"password\": \"cGFzc3dvcmQ=\",\n" +
                "\"id_geoDB\": \"Q483522\",\n" +
                "\"city\": \"Villach\"\n" +
                "}";

        try {
            MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
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
        try {
            when(service.updatePerson(DEFAULT_ID,defaultPerson1_2)).thenReturn(defaultPerson1_2);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }

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
            MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
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
        try {
            when(service.updatePerson(DEFAULT_EMAIL,defaultPerson1_2)).thenReturn(defaultPerson1_2);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }

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
            MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
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
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        try {
            when(service.deletePerson(DEFAULT_ID)).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders
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
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        try {
            when(service.deletePerson(DEFAULT_EMAIL)).thenReturn(response);
            mockMvc.perform(MockMvcRequestBuilders
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