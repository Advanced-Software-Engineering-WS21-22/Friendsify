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

//    @Autowired
//    private FriendsDao friendsDao;

    public FriendsController(BusinessLogicFriends friendsDao){
        this.businessLogicFriends = friendsDao;
    }

    @GetMapping()
    public List<Friends> listFriendships() {
//        return friendsDao.findAll();
        return businessLogicFriends.getAllFriendship();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friends> getFriendshipById(@PathVariable("id") Long id)
            throws ResourceNotFoundException {

//        Friends f = friendsDao.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found for id: " + id));

        Friends f = businessLogicFriends.getById(id);
        return ResponseEntity.ok(f);
    }

    @GetMapping(params = "email_initiator")
    public List<Friends> getFriendshipsByEmailInitiator(@RequestParam String email_initiator)
            throws ResourceNotFoundException {
//        List<Friends> friends_of_initiator = new ArrayList<>();
//        for(Friends f: friendsDao.findAll()){
//            if(f.getEmail_p_initiator().equals(email_initiator)){
//                friends_of_initiator.add(f);
//            }
//        }
//        if(friends_of_initiator.isEmpty()){
//            throw  new ResourceNotFoundException("No Friendships with this initiator email found: "+ email_initiator);
//        }
//        return friends_of_initiator;
        return businessLogicFriends.getByEmailInitiator(email_initiator);
    }

    @GetMapping(params = "email_friend")
    public List<Friends> getFriendshipsByEmailFriend(@RequestParam String email_friend)
            throws ResourceNotFoundException {
//        List<Friends> friends = new ArrayList<>();
//        for(Friends f: friendsDao.findAll()){
//            if(f.getEmail_p_initiator().equals(email_friend)){
//                friends.add(f);
//            }
//        }
//        if(friends.isEmpty()){
//            throw  new ResourceNotFoundException("No Friendships with this initiator email found: "+ email_friend);
//        }
//        return friends;
        return businessLogicFriends.getByEmailFriend(email_friend);
    }

    @GetMapping(params = { "email_initiator", "email_friend" })
    public Long getID(@RequestParam String email_initiator, @RequestParam String email_friend) throws  ResourceNotFoundException{
//        for(Friends f: friendsDao.findAll()){
//            if(f.getEmail_p_initiator().equals(email_initiator) && f.getEmail_p_friend().equals(email_friend)){
//                return f.getId_friend();
//            }
//        }
//        throw new ResourceNotFoundException("Friendship with email_initiator = "+email_initiator+" and email_friend = "+email_friend+" was not found.");
        return businessLogicFriends.getID(email_initiator,email_friend);
    }

    @PostMapping()
    public Friends createFriendship(@Valid @RequestBody Friends friends) throws InvalidDataException{
//        if(checkForDuplicate(friends)){
//            throw new InvalidDataException("Friendship is already in DB.");
//        }else  if(checkFriendshipToItself(friends)){
//            throw  new InvalidDataException("Friendship to itself is not allowed.");
//        }
//        return friendsDao.save(friends);
        return businessLogicFriends.saveFriendship(friends);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Friends> updateFriendship(@PathVariable("id") Long id,
                                                    @Valid @RequestBody Friends friendsDto)
            throws ResourceNotFoundException, InvalidDataException {


//        Friends origFriends = friendsDao.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found for id: " + id));
//
//        if(checkForDuplicate(friendsDto)){
//            throw new InvalidDataException("Friendship is already in DB.");
//        }else if(checkFriendshipToItself(friendsDto)){
//            throw  new InvalidDataException("Friendship to itself is not allowed.");
//        }
//
//        origFriends.updateFromDto(friendsDto);

//        Friends updatedLecture = friendsDao.save(origFriends);
        Friends updatedLecture = businessLogicFriends.update(id, friendsDto);
        return ResponseEntity.ok(updatedLecture);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteFriendship(@PathVariable("id") Long id)
            throws ResourceNotFoundException {

//        Friends origFriends = friendsDao.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found for id: " + id));
//
//        friendsDao.delete(origFriends);
        String ms = businessLogicFriends.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put(ms, Boolean.TRUE);
        return response;
    }

//    private boolean checkForDuplicate(Friends friends){
//        for(Friends f: friendsDao.findAll()){
//            if(f.getEmail_p_initiator().equals(friends.getEmail_p_initiator()) && f.getEmail_p_friend().equals(friends.getEmail_p_friend())){
//                return true;
//            }
//        }
//        return false;
//    }
//    private boolean checkFriendshipToItself(Friends friends){
//        return friends.getEmail_p_initiator().equals(friends.getEmail_p_friend());
//    }

}
