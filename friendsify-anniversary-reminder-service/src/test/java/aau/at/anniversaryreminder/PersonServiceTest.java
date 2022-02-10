package aau.at.anniversaryreminder;

import aau.at.anniversaryreminder.obj.Person;
import aau.at.anniversaryreminder.service.PersonServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PersonServiceImpl personService;

    private String email;
    private String name;
    private String surName;
    private LocalDate birthday;
    private Person samplePerson;
    private String host;

    @BeforeEach
    public void setup() {
        this.host = "samplehost";
        this.email = "pesron@mail";
        this.name = "Max";
        this.surName = "Mustermann";
        this.birthday = LocalDate.parse("1990-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        this.samplePerson = new Person(name, surName, birthday, email);
        this.personService = new PersonServiceImpl(host, restTemplate);
    }

    @AfterEach
    public void tearDown() {
        this.host = null;
        this.email = null;
        this.name = null;
        this.surName = null;
        this.birthday = null;
        this.samplePerson = null;
        this.personService = null;
    }

    @Test
    void testGetPersonViaEmail() {
        String filter = "?email="+email;
        Mockito.when(restTemplate.getForObject(host + filter, Person[].class)).thenReturn(new Person[]{this.samplePerson});
        Assertions.assertEquals("Max Mustermann", personService.getNameByEmail(email));
        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(host + filter, Person[].class);

    }

}
