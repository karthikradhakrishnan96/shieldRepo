package com.endurance.shield.dbserver.users;

import com.endurance.shield.dbserver.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by kai on 16/6/17.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment env;

    void createUser(User user) {
        System.out.println(this.userRepository.findAll());
        System.out.println(userExists(user.getUsername()));
        if(userExists(user.getUsername()) == null) {
              System.out.println("Inside");
              user.setPassword(BCrypt.hashpw(user.getPassword(),env.getProperty("password.hash.key")));
              System.out.println("USER: "+user);
              this.userRepository.save(user);
          }
    }

    boolean verify(String userName, String password)
    {
        System.out.println(this.userRepository.findAll());
        User user = (User)this.userRepository.findOne(userName);

        //return user != null && user.getPassword().equals(password);
        return user != null && BCrypt.checkpw(password,user.getPassword());
    }

    public User userExists(String userName) {
        System.out.println(this.userRepository.findAll());
        return this.userRepository.findOne(userName);
    }

    public void makeBoth(String username) {
        System.out.println(this.userRepository.findAll());

        System.out.println(userRepository.findAll());
        User user = this.userRepository.findOne(username);
        user.setSquad(Squad.BOTH);
        userRepository.save(user);
    }
}
