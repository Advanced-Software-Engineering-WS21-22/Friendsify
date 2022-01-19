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
import java.util.Objects;

@Service
public class FriendsifyUserDetailsService implements UserDetailsService {


    @Autowired
    private PersonRepository personRepository = PersonRepository.getPersonRepository();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new FriendsifyUser(this.findUser(username), true, false, false, false, authorities);
    }

    private Person findUser(String username) {
        Person found = null;
        for (Person p: this.personRepository.getPersons()) {
            if(Objects.equals(username, p.getEmail())) {
                found = p;
            }
        }
        return found;
    }
}
