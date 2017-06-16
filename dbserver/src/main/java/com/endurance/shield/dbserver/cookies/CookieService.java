package com.endurance.shield.dbserver.cookies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kai on 16/6/17.
 */
@Service
public class CookieService {
    @Autowired
    private CookieRepository cookieRepository;

    public void createCookie(Cookie cookie) {
        cookieRepository.save(cookie);
    }

    public void deleteCookie(Cookie cookie) {
        cookieRepository.delete(cookie);
    }

    public boolean checkCookie(Cookie cookie) {
        return cookieRepository.findOne(cookie.getCookie())!=null;
    }

    public List<Cookie> getAll() {
        return (List<Cookie>) this.cookieRepository.findAll();
    }
}
