package aau.at.friendsifyfrontendservice.authentication;

import aau.at.friendsifyfrontendservice.models.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class FriendsifyUser extends User implements UserDetails {

    private Person person;

    public FriendsifyUser(Person person, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(person.getEmail(), person.getPassword_hash(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.person = person;
    }

    public String getFirstName() {
        return this.person.getFirstName();
    }

    public String getLastName() {
        return this.person.getLastName();
    }

    public Person getPerson() {
        return this.person;
    }

    @Override
    public String getPassword() {
        return this.person.getPassword_hash();
    }
}
