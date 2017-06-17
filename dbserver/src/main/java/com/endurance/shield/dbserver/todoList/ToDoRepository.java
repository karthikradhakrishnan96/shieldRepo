package com.endurance.shield.dbserver.todoList;

import com.endurance.shield.dbserver.users.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by kai on 16/6/17.
 */
public interface ToDoRepository extends CrudRepository<ToDoList,Integer> {
    List<ToDoList> findDistinctByUsernameOrTypeIn(String username, List<Type> types);

}
