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

    public Cookie() {
    }

    public Cookie(String cookie) {
        this.cookie = cookie;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
