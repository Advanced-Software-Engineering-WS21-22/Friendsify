package aau.at.friendsifyfriendsservice.controller;

import aau.at.friendsifyfriendsservice.businesslogic.BusinessLogicFriends;
import aau.at.friendsifyfriendsservice.exceptions.InvalidDataException;
import aau.at.friendsifyfriendsservice.exceptions.ResourceNotFoundException;
import aau.at.friendsifyfriendsservice.model.Friends;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/friends")
public class FriendsController {
    private final BusinessLogicFriends businessLogicFriends;

    public FriendsController(BusinessLogicFriends friendsDao){
        this.businessLogicFriends = friendsDao;
    }

    @GetMapping()
    public List<Friends> listFriendships() {
        return businessLogicFriends.getAllFriendship();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friends> getFriendshipById(@PathVariable("id") Long id)
            throws ResourceNotFoundException {

        Friends f = businessLogicFriends.getById(id);
        return ResponseEntity.ok(f);
    }

    @GetMapping(params = "email_initiator")
    public List<Friends> getFriendshipsByEmailInitiator(@RequestParam String email_initiator)
            throws ResourceNotFoundException {

        return businessLogicFriends.getByEmailInitiator(email_initiator);
    }

    @GetMapping(params = "email_friend")
    public List<Friends> getFriendshipsByEmailFriend(@RequestParam String email_friend)
            throws ResourceNotFoundException {

        return businessLogicFriends.getByEmailFriend(email_friend);
    }

    @GetMapping(params = { "email_initiator", "email_friend" })
    public Long getID(@RequestParam String email_initiator, @RequestParam String email_friend) throws  ResourceNotFoundException{

        return businessLogicFriends.getID(email_initiator,email_friend);
    }

    @PostMapping()
    public Friends createFriendship(@Valid @RequestBody Friends friends) throws InvalidDataException{

        return businessLogicFriends.saveFriendship(friends);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Friends> updateFriendship(@PathVariable("id") Long id,
                                                    @Valid @RequestBody Friends friendsDto)
            throws ResourceNotFoundException, InvalidDataException {

        Friends updatedLecture = businessLogicFriends.update(id, friendsDto);
        return ResponseEntity.ok(updatedLecture);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteFriendship(@PathVariable("id") Long id)
            throws ResourceNotFoundException {

        String ms = businessLogicFriends.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put(ms, Boolean.TRUE);
        return response;
    }
}
