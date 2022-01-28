package aau.at.friendsifybirthdayservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoBirthdayException extends Exception {

    public NoBirthdayException() {
        super("This person's birthday is not today");
    }

}
