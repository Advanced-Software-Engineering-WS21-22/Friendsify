package aau.at.friendsifypersonservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends  Exception{
    public PersonNotFoundException(String message) {
        super(message);
    }
}
