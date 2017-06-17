package com.endurance.shield.dbserver.todoList;

import com.endurance.shield.dbserver.users.User;
import com.endurance.shield.dbserver.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by adeshk on 16/6/17.
 */
@Service
class ToDoListService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private UserService userService;

    boolean createList(ToDoList toDoList) {
        if(userService.userExists(toDoList.getUsername())!=null) {
            toDoRepository.save(toDoList);
            return true;
        }
        return false;
    }

    List<ToDoList> getToDosByUser(String username) {
        User user = userService.userExists(username);
        System.out.println(user);
        return toDoRepository.findDistinctByUsernameOrTypeIn(username,user.getSquad().getTypes());

    }

    void close(ToDoList toDoList) {
        toDoList.setClosed(true);
        toDoRepository.save(toDoList);
    }
}
