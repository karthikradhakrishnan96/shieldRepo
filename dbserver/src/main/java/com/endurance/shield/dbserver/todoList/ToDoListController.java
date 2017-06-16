package com.endurance.shield.dbserver.todoList;

import com.endurance.shield.dbserver.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by adeshk on 16/6/17.
 */
@RestController
public class ToDoListController {
    @Autowired
    private ToDoListService toDoListService;

    public ToDoListController() {
    }

    @RequestMapping(
            value = {"/createItem"},
            method = {RequestMethod.POST}
    )
    public void createItem(@RequestBody ToDoList toDoList){
        this.toDoListService.createList();
    }

    @RequestMapping(
            value = {"/getUserItem"},
            method = {RequestMethod.GET}
    )
    public List<ToDoList> getUserList(@RequestParam User user){
        return this.toDoListService.
    }
}
