package aau.at.friendsifyfrontendservice.exceptions;

public class PasswordMatchException extends Exception {

    public PasswordMatchException(String errorMessage) {
        super(errorMessage);
    }
}
