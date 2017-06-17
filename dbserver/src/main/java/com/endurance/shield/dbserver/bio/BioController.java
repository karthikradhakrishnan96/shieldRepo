package com.endurance.shield.dbserver.bio;

import com.endurance.shield.dbserver.users.Squad;
import com.endurance.shield.dbserver.users.User;
import com.endurance.shield.dbserver.users.UserService;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by adeshk on 17/6/17.
 */
@RestController
public class BioController {

    @Autowired
    private BioService bioService;



    public BioController(){

    }

    @RequestMapping(value = "/getBio/{username}",method = RequestMethod.GET)
    public Bio getBio(@PathVariable String username){
        return bioService.getBio(username);
    }

    @RequestMapping(value = "/createBio",method = RequestMethod.POST)
    public void createBio(@RequestBody Bio bio){

        bioService.createBio(bio);
    }

    @RequestMapping(value = "/updateBio",method = RequestMethod.PUT)
    public void updateBio(@RequestBody Bio bio){
        bioService.updateBio(bio);
    }

    @RequestMapping(value = "/getAllBio",method = RequestMethod.GET)
    public List<Bio> getAllBio(@RequestParam String username){
        return bioService.getAllBio(username);
    }
}
