package aau.at.friendsifyemailservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailDAO extends JpaRepository<Email, Long> {
}
