package aau.at.friendsifyjokeservice.obj;

import lombok.Data;

@Data
public class Joke{
    private String description;
    private String language;
    private String background;
    private String category;
    private String date;
    private JokeContent joke;
}