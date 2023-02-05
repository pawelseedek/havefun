package trizu.havefun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import trizu.havefun.domain.User;
import trizu.havefun.repository.UserRepository;
import trizu.havefun.service.UserService;

@Repository
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }

    public void save(User user){
        userRepository.save(user);
    }

    public Boolean existsUserByUsername(String username){
        return userRepository.existsUserByUsername(username);
    }
}
