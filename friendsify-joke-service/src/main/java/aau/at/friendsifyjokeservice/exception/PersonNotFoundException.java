package aau.at.friendsifyjokeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(Long personId) {
        super("Person not found with id " + personId);
    }
}
