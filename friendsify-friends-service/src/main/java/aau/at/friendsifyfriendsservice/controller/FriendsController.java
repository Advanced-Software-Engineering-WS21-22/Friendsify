package aau.at.friendsifyfriendsservice.controller;

import aau.at.friendsifyfriendsservice.ResourceNotFoundException;
import aau.at.friendsifyfriendsservice.model.Friends;
import aau.at.friendsifyfriendsservice.repositories.FriendsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<Friends> listLectures() {
        return friendsDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friends> getLectureById(@PathVariable("id") Long id)
            throws ResourceNotFoundException {

        Friends f = friendsDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found for id: " + id));
        return ResponseEntity.ok(f);
    }

    @PostMapping()
    public Friends createLecture(@Valid @RequestBody Friends friends) {

        return friendsDao.save(friends);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Friends> updateLecture(@PathVariable("id") Long id,
                                                 @Valid @RequestBody Friends friendsDto)
            throws ResourceNotFoundException{


        Friends origLecture = friendsDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found for id: " + id));

        origLecture.updateFromDto(friendsDto);

        Friends updatedLecture = friendsDao.save(origLecture);
        return ResponseEntity.ok(updatedLecture);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteLecture(@PathVariable("id") Long id)
            throws ResourceNotFoundException {

        Friends origfriends = friendsDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found for id: " + id));

        friendsDao.delete(origfriends);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
