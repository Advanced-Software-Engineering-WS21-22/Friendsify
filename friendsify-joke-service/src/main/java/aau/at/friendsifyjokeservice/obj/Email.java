package aau.at.friendsifyjokeservice.obj;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Email {
    private String from;
    private String to;
    private String subject;
    private String text;
}
