package aau.at.friendsifyfrontendservice.authenticationTests;

import aau.at.friendsifyfrontendservice.authentication.FriendsifyUser;
import aau.at.friendsifyfrontendservice.authentication.FriendsifyUserDetailsService;
import aau.at.friendsifyfrontendservice.models.Person;
import aau.at.friendsifyfrontendservice.services.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {

    @Mock
    PersonService personServiceMock;

    @InjectMocks
    FriendsifyUserDetailsService friendsifyUserDetailsServiceMock;

    @Test
    public void testFindPerson() {
        Person person = new Person(1L, "Max", "Mustermann", "test@email.at", LocalDate.now(), "Q123", "Klagenfurt", "test");
        when(this.personServiceMock.getPersonByEMail("test@email.at")).thenReturn(person);
        List authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        FriendsifyUser expected = new FriendsifyUser(person, true, false, false, false, authorities);

        Assert.assertEquals(expected, friendsifyUserDetailsServiceMock.loadUserByUsername("test@email.at"));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testFindPersonNotFound() {
        when(this.personServiceMock.getPersonByEMail("test@email.at")).thenThrow(new UsernameNotFoundException("Not found"));
        List authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        this.friendsifyUserDetailsServiceMock.loadUserByUsername("test@email.at");
    }


}
