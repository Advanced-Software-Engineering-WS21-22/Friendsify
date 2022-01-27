package aau.at.friendsifybirthdayservice.obj;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class Email {
    private String from;
    private String to;
    private String subject;
    private String text;
}