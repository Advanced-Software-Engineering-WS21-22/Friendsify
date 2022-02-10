package aau.at.friendsifyfriendsservice.businesslogic;

import aau.at.friendsifyfriendsservice.exceptions.*;
import aau.at.friendsifyfriendsservice.model.Friends;
import aau.at.friendsifyfriendsservice.repositories.FriendsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessLogicFriends {

    @Autowired
    private final FriendsDao friendsDao;

    public BusinessLogicFriends (FriendsDao friendsDao){
        this.friendsDao = friendsDao;
    }

    public List<Friends> getAllFriendship(){
        return friendsDao.findAll();
    }

    public Friends getById(Long id) throws ResourceNotFoundException{
        return friendsDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Friendship not found for id: " + id));
    }
    public List<Friends> getByEmailInitiator(String email_initiator) throws ResourceNotFoundException {
        List<Friends> friends_of_initiator = new ArrayList<>();
        for(Friends f: friendsDao.findAll()){
            if(f.getEmail_p_initiator().equals(email_initiator)){
                friends_of_initiator.add(f);
            }
        }
        if(friends_of_initiator.isEmpty()){
            throw  new ResourceNotFoundException("No Friendships with this initiator email found: "+ email_initiator);
        }
        return friends_of_initiator;
    }

    public List<Friends> getByEmailFriend(String email_friend) throws ResourceNotFoundException{
        List<Friends> friends = new ArrayList<>();
        for(Friends f: friendsDao.findAll()){
            if(f.getEmail_p_friend().equals(email_friend)){
                friends.add(f);
            }
        }
        if(friends.isEmpty()){
            throw  new ResourceNotFoundException("No Friendships with this initiator email found: "+ email_friend);
        }
        return friends;
    }
    public Long getID(String email_initiator, String email_friend)throws ResourceNotFoundException{
        for(Friends f: friendsDao.findAll()){
            if(f.getEmail_p_initiator().equals(email_initiator) && f.getEmail_p_friend().equals(email_friend)){
                return f.getId_friend();
            }
        }
        throw new ResourceNotFoundException("Friendship with email_initiator = "+email_initiator+" and email_friend = "+email_friend+" was not found.");
    }
    public Friends saveFriendship(Friends friends) throws InvalidDataException{
        if(checkForDuplicate(friends)){
            throw new InvalidDataException("Friendship is already in DB.");
        }else  if(checkFriendshipToItself(friends)){
            throw  new InvalidDataException("Friendship to itself is not allowed.");
        }
        return friendsDao.save(friends);
    }
    public Friends update(Long id, Friends friends) throws ResourceNotFoundException, InvalidDataException {

        Friends origFriends = getById(id);

        if(checkForDuplicate(friends)){
            throw new InvalidDataException("Friendship is already in DB.");
        }else if(checkFriendshipToItself(friends)){
            throw  new InvalidDataException("Friendship to itself is not allowed.");
        }
        origFriends.updateFromDto(friends);

        return friendsDao.save(origFriends);
    }

    public String delete(Long id) throws ResourceNotFoundException {
        Friends origFriends = getById(id);
        friendsDao.delete(origFriends);
        return "deleted";
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
