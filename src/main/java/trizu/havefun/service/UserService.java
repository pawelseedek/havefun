package trizu.havefun.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import trizu.havefun.domain.User;

public interface UserService extends UserDetailsService {
    void save(User user);
    Boolean existsUserByUsername(String username);
}
