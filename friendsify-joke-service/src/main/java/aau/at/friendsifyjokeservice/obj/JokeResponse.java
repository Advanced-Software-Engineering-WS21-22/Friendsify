package aau.at.friendsifyjokeservice.obj;

import lombok.Data;

@Data
public class JokeResponse {
    private Success success;
    private Contents contents;
}