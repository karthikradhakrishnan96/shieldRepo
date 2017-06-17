package com.endurance.shield.dbserver.todoList;

import com.endurance.shield.dbserver.users.User;
import com.endurance.shield.dbserver.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public void createItem(@RequestBody ToDoList toDoList, HttpServletResponse servletResponse)
    {
        boolean created = this.toDoListService.createList(toDoList);
        if(!created)
            servletResponse.setStatus(400);
    }

    @RequestMapping(
            value = {"/getUserItem"},
            method = {RequestMethod.GET}
    )
    public List<ToDoList> getToDoList(@RequestParam String username){
        return this.toDoListService.getToDosByUser(username);
    }
    @RequestMapping(
            value = {"/markAsDone"},
            method = {RequestMethod.POST}
    )
    public void markAsDone(@RequestBody ToDoList toDoList){
        toDoListService.close(toDoList);
    }
}
