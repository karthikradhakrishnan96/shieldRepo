package com.endurance.shield.dbserver.todoList;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;


@Entity
public class ToDoList{

    @Id
    @SequenceGenerator(name = "seq_contacts", sequenceName = "seq_contacts")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_contacts")
    private int id;

    private String task;
    private Type type;

    @Value("false")
    private boolean isClosed;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

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


    public ToDoList(String task, Type type, String username) {
        this.task = task;
        this.type = type;
        this.username = username;
    }
}