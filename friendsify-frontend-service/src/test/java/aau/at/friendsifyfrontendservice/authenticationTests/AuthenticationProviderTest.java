package aau.at.friendsifyfrontendservice.authenticationTests;

import aau.at.friendsifyfrontendservice.authentication.FriendsifyAuthenticationProvider;
import aau.at.friendsifyfrontendservice.authentication.FriendsifyUser;
import aau.at.friendsifyfrontendservice.models.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationProviderTest {

    @Mock
    UserDetailsService userDetailsService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    Authentication authentication;

    @Mock
    UserDetails userDetails;

    @InjectMocks
    FriendsifyAuthenticationProvider friendsifyAuthenticationProvider;

    @Before
    public void setUp() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test(expected = BadCredentialsException.class)
    public void testNoUserPassedAuthentication() {
        FriendsifyUser user = this.createFriendsifyUser();
        when(authentication.getPrincipal()).thenReturn(user);
        when(authentication.getName()).thenReturn("");

        friendsifyAuthenticationProvider.authenticate(authentication);
    }


    private FriendsifyUser createFriendsifyUser() {
        Person person =  new Person(1L, "Max", "Mustermann", "max@mustermann.de", LocalDate.now(), "Q1234", "Klagenfurt", "password_hash");
        List authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new FriendsifyUser(person, true, false, false, false, authorities);
    }

}
