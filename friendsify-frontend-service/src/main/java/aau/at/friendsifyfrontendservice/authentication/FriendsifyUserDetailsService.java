package aau.at.friendsifyfrontendservice.authentication;

import aau.at.friendsifyfrontendservice.dataSamples.PersonRepository;
import aau.at.friendsifyfrontendservice.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        this.personRepository = PersonRepository.getPersonRepository();
        List authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new FriendsifyUser(this.findUser(username), true, false, false, false, authorities);
    }

    private Person findUser(String username) {
        for (Person p: this.personRepository.getPersons()) {
            if(username.equals(p.getEmail())) {
                return p;
            }
        }
        return null;
    }
}
