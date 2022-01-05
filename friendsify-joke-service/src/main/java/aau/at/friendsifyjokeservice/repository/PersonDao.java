package aau.at.friendsifyjokeservice.repository;

import aau.at.friendsifyjokeservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Long> {


}