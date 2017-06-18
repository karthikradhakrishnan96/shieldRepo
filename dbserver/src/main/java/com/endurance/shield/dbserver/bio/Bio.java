package com.endurance.shield.dbserver.bio;

import com.endurance.shield.dbserver.todoList.Type;
import com.endurance.shield.dbserver.users.Squad;

import javax.persistence.*;

/**
 * Created by adeshk on 17/6/17.
 */
@Entity
public class Bio {

    @Id
    @SequenceGenerator(name = "seq_contacts", sequenceName = "seq_contacts")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_contacts")
    private int id;
    @Column(name="userName")
    private String userName;
    private String bioHtml;
    @Column(name="type")
    private Type type;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBioHtml() {
        return bioHtml;
    }

    public void setBioHtml(String bioHtml) {
        this.bioHtml = bioHtml;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Bio(String userName, String bioHtml, Type type) {

        this.userName = userName;
        this.bioHtml = bioHtml;
        this.type = type;
    }

    public Bio() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
