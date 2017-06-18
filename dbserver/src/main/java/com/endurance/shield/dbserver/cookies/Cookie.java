package com.endurance.shield.dbserver.cookies;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * Created by kai on 16/6/17.
 */
@Entity
public class Cookie {
    @Id
    private String cookie;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Cookie() {
    }

    public Cookie(String cookie,String username) {
        this.cookie = cookie;
        this.username=username;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
