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
    private String userName;
    private String bioHtml;
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



}
