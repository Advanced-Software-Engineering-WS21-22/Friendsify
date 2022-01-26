package aau.at.friendsifyfrontendservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {

    private String from;

    private String to;

    private String subject;

    private String text;

    public Email() {

    }

    public Email(String from, String to, String subject, String text) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Email{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

}
