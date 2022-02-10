package aau.at.friendsifyfriendsservice.repositories;

import aau.at.friendsifyfriendsservice.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendsDao extends JpaRepository<Friends, Long> {
}
