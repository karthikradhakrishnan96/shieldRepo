package com.endurance.shield.dbserver.todoList;
import com.endurance.shield.dbserver.users.User;

import javax.persistence.*;


@Entity
public class ToDoList{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String task;
    private Type type;
    private boolean isClosed;
    @ManyToOne
    private User user;

    public ToDoList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ToDoList(int id, String task, Type type, boolean isClosed, User user) {

        this.id = id;
        this.task = task;
        this.type = type;
        this.isClosed = isClosed;
        this.user = user;
    }
}