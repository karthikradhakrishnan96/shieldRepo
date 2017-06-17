package com.endurance.shield.dbserver.users;

import org.eclipse.jetty.client.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by kai on 16/6/17.
 */

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {
    }

    @RequestMapping(
            value = {"/createUser"},
            method = {RequestMethod.POST}
    )
    public void createUser(@RequestBody User user)
    {
        this.userService.createUser(user);
    }

    @RequestMapping(
            value = {"/loginUser"},
            method = {RequestMethod.POST}
    )
    public void loginUser(@RequestBody Map<String,String> json, HttpServletResponse httpResponse)
    {
        String userName = json.get("username");
        String password = json.get("password");
        boolean verified = userService.verify(userName,password);
        if(verified)
        {
            httpResponse.setStatus(200);
        }
        else
        {
            httpResponse.setStatus(401);
        }
    }
    @RequestMapping(
            value = {"/checkUser"},
            method = {RequestMethod.GET}
    )
    public User checkUser(@RequestParam String username, HttpServletResponse httpResponse)
    {
        User user = userService.userExists(username);
        if(user == null)
        {
            httpResponse.setStatus(404);
        }
        else{
            httpResponse.setStatus(200);
        }
        return user;
    }
    @RequestMapping(
            value = "/makeBoth",
            method = RequestMethod.POST
    )
    public void makeBoth(@RequestBody String username)
    {
        System.out.println(username+" Got in makeBoth");
        //TODO: Fix Unirest
        username = username.split("=")[1];
        userService.makeBoth(username);
    }

}
