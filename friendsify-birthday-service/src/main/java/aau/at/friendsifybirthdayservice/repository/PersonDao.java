package aau.at.friendsifybirthdayservice.repository;

import aau.at.friendsifybirthdayservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PersonDao extends JpaRepository<Person, Long> {

    List<Person> findByBirthday(LocalDate date);

}
