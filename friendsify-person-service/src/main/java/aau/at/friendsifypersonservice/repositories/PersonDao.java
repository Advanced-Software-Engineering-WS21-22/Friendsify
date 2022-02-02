package aau.at.friendsifypersonservice.repositories;

import aau.at.friendsifypersonservice.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonDao extends JpaRepository<Person,Long>{
    public Person findByEmail(String email);
}
