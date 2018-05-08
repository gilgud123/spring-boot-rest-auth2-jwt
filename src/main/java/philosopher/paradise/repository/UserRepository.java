package philosopher.paradise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import philosopher.paradise.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
}
