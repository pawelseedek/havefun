package trizu.havefun.repository;

import org.springframework.data.repository.CrudRepository;
import trizu.havefun.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsUserByUsername(String username);
}
