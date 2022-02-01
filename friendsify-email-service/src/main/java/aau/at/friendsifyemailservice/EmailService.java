package aau.at.friendsifyemailservice;

public interface EmailService {

    void sendEmail(String to, String from, String subject, String text);
}
