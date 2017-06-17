package com.endurance.shield.dbserver.bio;

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
    private String bioHtml;
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBioHtml() {
        return bioHtml;
    }

    public void setBioHtml(String bioHtml) {
        this.bioHtml = bioHtml;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Bio(int id, String bioHtml, String userName) {

        this.id = id;
        this.bioHtml = bioHtml;
        this.userName = userName;
    }

    public Bio() {

    }
}
