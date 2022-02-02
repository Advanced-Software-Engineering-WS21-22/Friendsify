package aau.at.friendsifypersonservice.servicelogics;

import aau.at.friendsifypersonservice.exceptions.PersonNotFoundException;
import aau.at.friendsifypersonservice.models.Person;
import aau.at.friendsifypersonservice.repositories.PersonDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceLogicTest {

    @Mock
    private PersonDao personDao;

    @InjectMocks
    private PersonServiceLogic service;

    List<Person> default_people;
    Person defaultPerson1;
    Person defaultPerson2;
    Person defaultPerson3;
    Person defaultPerson4;

    static final Long DEFAULT_ID =1L;
    static final Long DEFAULT_ID_NOT_AVAILABLE =6L;
    static final String DEFAULT_EMAIL = "max@mustermann.de";
    static final String DEFAULT_EMAIL_NOT_AVAILABLE = "clara@nussknacker.de";
    static final String DEFAULT_EXCEPTION_ID ="Person not found by id: " + DEFAULT_ID_NOT_AVAILABLE;
    static final String DEFAULT_EXCEPTION_EMAIL ="Person not found by email: " + DEFAULT_EMAIL_NOT_AVAILABLE;

    @BeforeEach
    void setUp() {
        defaultPerson1 = new Person(1L,"Max", "Mustermann", LocalDate.of(2000,1,1), "max@mustermann.de", "cGFzc3dvcmQ= ", "Q483522", "Villach");
        defaultPerson2 =new Person(1L,"Anna", "Mustermann", LocalDate.of(2001,1,1), "anna@mustermann.de", "cGFzc3dvcmQ", "Q483522", "Villach");
        defaultPerson3 =new Person(3L,"John", "Doe", LocalDate.of(1990,6,6), "john.doe@email.com", "cGFzc3dvcmQ", "Q41753", "Klagenfurt");
        defaultPerson4 =new Person(4L,"Hans", "Müller", LocalDate.of(1994,8,18), "hans.m@gmail.com", "cGFzc3dvcmQ", "Q660687", "Velden am Wörthersee");
        default_people =new ArrayList<>();
        default_people.add(defaultPerson1);
        default_people.add(defaultPerson3);
        default_people.add(defaultPerson4);
    }

    @AfterEach
    void tearDown() {
        default_people =null;
        defaultPerson1 =null;
        defaultPerson2 =null;
        defaultPerson3 =null;
        defaultPerson4 =null;
    }

    @Test
    void getAllPersons() {
        when(personDao.findAll()).thenReturn(default_people);
        assertThat(service.getAllPersons()).isNotNull().isEqualTo(default_people);
        verify(personDao,times(1)).findAll();
    }

    @Test
    void getPersonByID() {
        when(personDao.findById(DEFAULT_ID)).thenReturn(java.util.Optional.ofNullable(defaultPerson1));
        try {
            assertThat(service.getPersonByID(DEFAULT_ID)).isNotNull().isEqualTo(defaultPerson1);
            verify(personDao,times(1)).findById(DEFAULT_ID);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }
    }

    @Test
    void getPersonByIDFail() {
        PersonNotFoundException exception = assertThrows(PersonNotFoundException.class,()->{
            service.getPersonByID(DEFAULT_ID_NOT_AVAILABLE);
        });
        assertEquals( DEFAULT_EXCEPTION_ID,exception.getMessage());
        verify(personDao,times(1)).findById(DEFAULT_ID_NOT_AVAILABLE);
    }

    @Test
    void getPersonByEmail() {
        when(personDao.findByEmail(DEFAULT_EMAIL)).thenReturn(defaultPerson1);
        try {
            assertThat(service.getPersonByEmail(DEFAULT_EMAIL)).isNotNull().isEqualTo(defaultPerson1);
            verify(personDao,times(1)).findByEmail(DEFAULT_EMAIL);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }
    }

    @Test
    void getPersonByEmailFail() {
        PersonNotFoundException exception = assertThrows(PersonNotFoundException.class,()->{
            service.getPersonByEmail(DEFAULT_EMAIL_NOT_AVAILABLE);
        });
        assertEquals( DEFAULT_EXCEPTION_EMAIL,exception.getMessage());
        verify(personDao,times(1)).findByEmail(DEFAULT_EMAIL_NOT_AVAILABLE);
    }

    @Test
    void createPerson() {
        when(personDao.save(defaultPerson1)).thenReturn(defaultPerson1);
        assertThat(service.createPerson(defaultPerson1)).isNotNull().isEqualTo(defaultPerson1);
        verify(personDao,times(1)).save(defaultPerson1);
    }
    @Test
    void updatePersonId() {
        when(personDao.findById(DEFAULT_ID)).thenReturn(java.util.Optional.ofNullable(defaultPerson1));
        when(personDao.save(defaultPerson2)).thenReturn(defaultPerson2);
        try {
            assertThat(service.updatePerson(DEFAULT_ID, defaultPerson2)).isNotNull().isEqualTo(defaultPerson1);
            verify(personDao,times(1)).findById(DEFAULT_ID);
            verify(personDao,times(1)).save(defaultPerson2);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }
    }

    @Test
    void updatePersonEmail() {
        when(personDao.findByEmail(DEFAULT_EMAIL)).thenReturn(defaultPerson1);
        when(personDao.save(defaultPerson2)).thenReturn(defaultPerson2);
        try {
            assertThat(service.updatePerson(DEFAULT_EMAIL, defaultPerson2)).isNotNull().isEqualTo(defaultPerson1);
            verify(personDao,times(1)).findByEmail(DEFAULT_EMAIL);
            verify(personDao,times(1)).save(defaultPerson2);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }
    }

    @Test
    void deletePersonID() {
        when(personDao.findById(DEFAULT_ID)).thenReturn(java.util.Optional.ofNullable(defaultPerson1));

        int peopleSize= default_people.size();
        doAnswer(invocationOnMock -> {
            default_people.remove(defaultPerson1);
            return default_people;
        }).when(personDao).delete(defaultPerson1);

        Map<String,Boolean> response=null;
        Map<String,Boolean> expected= new HashMap<>();
        expected.put("deleted",true);
        try {
            response =service.deletePerson(DEFAULT_ID);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found.");
        }

        assertEquals(peopleSize-1, default_people.size());
        assertEquals(expected,response);

        verify(personDao,times(1)).findById(DEFAULT_ID);
        verify(personDao,times(1)).delete(defaultPerson1);
    }

    @Test
    void deletePersonEMail() {
        when(personDao.findByEmail(DEFAULT_EMAIL)).thenReturn(defaultPerson1);

        int peopleSize= default_people.size();
        doAnswer(invocationOnMock -> {
            default_people.remove(defaultPerson1);
            return default_people;
        }).when(personDao).delete(defaultPerson1);

        Map<String,Boolean> response=null;
        Map<String,Boolean> expected= new HashMap<>();
        expected.put("deleted",true);
        try {
            response =service.deletePerson(DEFAULT_EMAIL);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found.");
        }

        assertEquals(peopleSize-1, default_people.size());
        assertEquals(expected,response);

        verify(personDao,times(1)).findByEmail(DEFAULT_EMAIL);
        verify(personDao,times(1)).delete(defaultPerson1);
    }
}