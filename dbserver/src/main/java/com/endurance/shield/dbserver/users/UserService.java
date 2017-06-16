package com.endurance.shield.dbserver.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kai on 16/6/17.
 */

@Service
class UserService {

    @Autowired
    private UserRepository userRepository;

    void createUser(User user) {
          this.userRepository.save(user);
    }

    boolean verify(String userName, String password)
    {
        User user = (User)this.userRepository.findOne(userName);
        return user != null && user.getPassword().equals(password);
    }

    User userExists(String userName) {
        return this.userRepository.findOne(userName);
    }
}
