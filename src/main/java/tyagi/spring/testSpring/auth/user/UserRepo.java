package tyagi.spring.testSpring.auth.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
