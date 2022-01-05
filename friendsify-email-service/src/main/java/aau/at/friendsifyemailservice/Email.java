package aau.at.friendsifyemailservice;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Email {
    @Id
    int id;

    String from;
    String to;
    String text;

    public Email(){

    }
    public Email(int id, String from, String to, String text){
        this.id =id;
        this.from = from;
        this.to = to;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void updateFromDTO(Email other){
        this.setFrom(other.getFrom());
        this.setTo(other.getTo());
        this.setText(other.getText());
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
