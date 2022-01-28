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
import org.mockito.Mockito;
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

    List<Person> people;
    Person max;
    Person anna;
    Person john;
    Person hans;

    static final Long ID=1L;
    static final Long ID_NOT_AVAILABLE=6L;
    static final String EMail = "max@mustermann.de";
    static final String EMail_NOT_AVAILABLE = "clara@nussknacker.de";
    static final String exID="Person not found by id: " + ID_NOT_AVAILABLE;
    static final String exEMail="Person not found by email: " + EMail_NOT_AVAILABLE;

    @BeforeEach
    void setUp() {
        max= new Person(1L,"Max", "Mustermann", LocalDate.of(2000,1,1), "max@mustermann.de", "cGFzc3dvcmQ= ", "Q483522", "Villach");
        anna=new Person(1L,"Anna", "Mustermann", LocalDate.of(2001,1,1), "anna@mustermann.de", "cGFzc3dvcmQ", "Q483522", "Villach");
        john=new Person(3L,"John", "Doe", LocalDate.of(1990,6,6), "john.doe@email.com", "cGFzc3dvcmQ", "Q41753", "Klagenfurt");
        hans=new Person(4L,"Hans", "Müller", LocalDate.of(1994,8,18), "hans.m@gmail.com", "cGFzc3dvcmQ", "Q660687", "Velden am Wörthersee");
        people =new ArrayList<>();
        people.add(max);
        people.add(john);
        people.add(hans);
    }

    @AfterEach
    void tearDown() {
        people =null;
        max=null;
        anna=null;
        john=null;
        hans=null;
    }

    @Test
    void getAllPersons() {
        when(personDao.findAll()).thenReturn(people);
        assertThat(service.getAllPersons()).isNotNull().isEqualTo(people);
        verify(personDao,times(1)).findAll();
    }

    @Test
    void getPersonByID() {
        when(personDao.findById(ID)).thenReturn(java.util.Optional.ofNullable(max));
        try {
            assertThat(service.getPersonByID(ID)).isNotNull().isEqualTo(max);
            verify(personDao,times(1)).findById(ID);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }
    }

    @Test
    void getPersonByIDFail() {
        PersonNotFoundException exception = assertThrows(PersonNotFoundException.class,()->{
            service.getPersonByID(ID_NOT_AVAILABLE);
        });
        assertEquals(exception.getMessage(),exID);
        verify(personDao,times(1)).findById(ID_NOT_AVAILABLE);
    }

    @Test
    void getPersonByEmail() {
        when(personDao.findByEmail(EMail)).thenReturn(max);
        try {
            assertThat(service.getPersonByEmail(EMail)).isNotNull().isEqualTo(max);
            verify(personDao,times(1)).findByEmail(EMail);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }
    }

    @Test
    void getPersonByEmailFail() {
        PersonNotFoundException exception = assertThrows(PersonNotFoundException.class,()->{
            service.getPersonByEmail(EMail_NOT_AVAILABLE);
        });
        assertEquals(exception.getMessage(),exEMail);
        verify(personDao,times(1)).findByEmail(EMail_NOT_AVAILABLE);
    }

    @Test
    void createPerson() {
        when(personDao.save(max)).thenReturn(max);
        assertThat(service.createPerson(max)).isNotNull().isEqualTo(max);
        verify(personDao,times(1)).save(max);
    }
    @Test
    void updatePersonId() {
        when(personDao.findById(ID)).thenReturn(java.util.Optional.ofNullable(max));
        when(personDao.save(anna)).thenReturn(anna);
        try {
            assertThat(service.updatePerson(ID,anna)).isNotNull().isEqualTo(max);
            verify(personDao,times(1)).findById(ID);
            verify(personDao,times(1)).save(anna);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }
    }

    @Test
    void updatePersonEmail() {
        when(personDao.findByEmail(EMail)).thenReturn(max);
        when(personDao.save(anna)).thenReturn(anna);
        try {
            assertThat(service.updatePerson(EMail,anna)).isNotNull().isEqualTo(max);
            verify(personDao,times(1)).findByEmail(EMail);
            verify(personDao,times(1)).save(anna);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found!");
        }
    }

    @Test
    void deletePersonID() {
        when(personDao.findById(ID)).thenReturn(java.util.Optional.ofNullable(max));

        int peopleSize=people.size();
        doAnswer(invocationOnMock -> {
            people.remove(max);
            return people;
        }).when(personDao).delete(max);

        Map<String,Boolean> response=null;
        Map<String,Boolean> expected= new HashMap<>();
        expected.put("deleted",true);
        try {
            response =service.deletePerson(ID);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found.");
        }

        assertEquals(peopleSize-1, people.size());
        assertEquals(expected,response);

        verify(personDao,times(1)).findById(ID);
        verify(personDao,times(1)).delete(max);
    }

    @Test
    void DeletePersonEMail() {
        when(personDao.findByEmail(EMail)).thenReturn(max);

        int peopleSize=people.size();
        doAnswer(invocationOnMock -> {
            people.remove(max);
            return people;
        }).when(personDao).delete(max);

        Map<String,Boolean> response=null;
        Map<String,Boolean> expected= new HashMap<>();
        expected.put("deleted",true);
        try {
            response =service.deletePerson(EMail);
        } catch (PersonNotFoundException e) {
            fail("Person should have been found.");
        }

        assertEquals(peopleSize-1, people.size());
        assertEquals(expected,response);

        verify(personDao,times(1)).findByEmail(EMail);
        verify(personDao,times(1)).delete(max);
    }
}