package com.endurance.shield.dbserver.bio;

import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by adeshk on 17/6/17.
 */
@RestController
public class BioController {

    @Autowired
    private BioService bioService;

    public BioController(){

    }

    @RequestMapping(value = "/getBio/{id}",method = RequestMethod.GET)
    public Bio getBio(@PathVariable int id){
        return bioService.getBio(id);
    }

    @RequestMapping(value = "/createBio",method = RequestMethod.POST)
    public void createBio(@RequestParam Bio bio){
        bioService.createBio(bio);
    }

    @RequestMapping(value = "/updateBio/{id}",method = RequestMethod.PUT)
    public void updateBio(@RequestParam Bio bio){
        bioService.updateBio(bio);
    }

    @RequestMapping(value = "/getSquadBio",)
}
