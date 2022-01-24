package aau.at.friendsifyfriendsservice.controller;

import aau.at.friendsifyfriendsservice.exceptions.InvalidDataException;
import aau.at.friendsifyfriendsservice.exceptions.ResourceNotFoundException;
import aau.at.friendsifyfriendsservice.model.Friends;
import aau.at.friendsifyfriendsservice.repositories.FriendsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/friends")
public class FriendsController {
    private static final Logger LOG = LoggerFactory.getLogger(FriendsController.class);

    @Autowired
    private FriendsDao friendsDao;

    @GetMapping()
    public List<Friends> listFriendships() {
        return friendsDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friends> getFriendshipById(@PathVariable("id") Long id)
            throws ResourceNotFoundException {

        Friends f = friendsDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found for id: " + id));
        return ResponseEntity.ok(f);
    }

    @GetMapping("/query")
    public List<Friends> getFriendshipsByEmailInitiator(@RequestParam String email)
            throws ResourceNotFoundException {
        List<Friends> friends = new ArrayList<>();
        for(Friends f: friendsDao.findAll()){
            if(f.getEmail_p_initiator().equals(email)){
                friends.add(f);
            }
        }
        if(friends.isEmpty()){
            throw  new ResourceNotFoundException("No Friendships with this initator email found: "+email);
        }
        return friends;
    }

    @PostMapping()
    public Friends createFriendship(@Valid @RequestBody Friends friends) throws InvalidDataException{
        if(checkForDuplicate(friends)){
            throw new InvalidDataException("Friendship is already in DB.");
        }else  if(checkFriendshipToItself(friends)){
            throw  new InvalidDataException("Friendship to itself is not allowed.");
        }
        return friendsDao.save(friends);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Friends> updateFriendship(@PathVariable("id") Long id,
                                                    @Valid @RequestBody Friends friendsDto)
            throws ResourceNotFoundException, InvalidDataException {


        Friends origFriends = friendsDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found for id: " + id));

        if(checkForDuplicate(friendsDto)){
            throw new InvalidDataException("Friendship is already in DB.");
        }else if(checkFriendshipToItself(friendsDto)){
            throw  new InvalidDataException("Friendship to itself is not allowed.");
        }

        origFriends.updateFromDto(friendsDto);

        Friends updatedLecture = friendsDao.save(origFriends);
        return ResponseEntity.ok(updatedLecture);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteFriendship(@PathVariable("id") Long id)
            throws ResourceNotFoundException {

        Friends origfriends = friendsDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found for id: " + id));

        friendsDao.delete(origfriends);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    private boolean checkForDuplicate(Friends friends){
        for(Friends f: friendsDao.findAll()){
            if(f.getEmail_p_initiator().equals(friends.getEmail_p_initiator()) && f.getEmail_p_friend().equals(friends.getEmail_p_friend())){
                return true;
            }
        }
        return false;
    }
    private boolean checkFriendshipToItself(Friends friends){
        return friends.getEmail_p_initiator().equals(friends.getEmail_p_friend());
    }

}
