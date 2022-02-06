package aau.at.friendsifyfrontendservice.authentication;

import aau.at.friendsifyfrontendservice.models.Person;
import aau.at.friendsifyfrontendservice.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendsifyUserDetailsService implements UserDetailsService {


    @Autowired
    private PersonService personService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Person found = this.personService.getPersonByEMail(email);

        return new FriendsifyUser(found, true, false, false, false, authorities);
    }
}
