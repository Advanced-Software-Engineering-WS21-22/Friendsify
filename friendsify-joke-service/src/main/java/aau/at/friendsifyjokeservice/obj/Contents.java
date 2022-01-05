package aau.at.friendsifyjokeservice.obj;

import lombok.Data;

import java.util.List;
@Data
public class Contents{
    private List<Joke> jokes;
    private String copyright;
}