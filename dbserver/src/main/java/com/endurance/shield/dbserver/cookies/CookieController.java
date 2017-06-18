package com.endurance.shield.dbserver.cookies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by kai on 16/6/17.
 */
@RestController
public class CookieController {
    @Autowired
    private CookieService cookieService;

    public CookieController() {
    }

    @RequestMapping(value = "/createCookie",method = RequestMethod.POST)
    public void createCookie(@RequestBody Cookie cookie)
    {
        System.out.println(cookie);
        this.cookieService.createCookie(cookie);
    }
    @RequestMapping(value = "/deleteCookie",method = RequestMethod.DELETE)
    public void deleteCookie(@RequestBody Cookie cookie)
    {
        this.cookieService.deleteCookie(cookie);
    }
    @RequestMapping(value = "/checkCookie",method = RequestMethod.POST)
    public void checkCookie(@RequestBody Cookie cookie, HttpServletResponse httpServletResponse)
    {
        boolean result = this.cookieService.checkCookie(cookie);
        if(result)
            httpServletResponse.setStatus(200);
        else
            httpServletResponse.setStatus(404);
    }
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Cookie> getAll()
    {
        return this.cookieService.getAll();
    }

    @RequestMapping(value = "/getCookieUser",method = RequestMethod.POST)
    public String getCookieUser(Cookie cookie){ return this.cookieService.getCookieUser(cookie);}

    @RequestMapping(value = "/getActiveSession",method = RequestMethod.POST)
    public List<Cookie> getActiveSession(@RequestBody Cookie cookie){
        System.out.println(cookie+" is the cookie");
        String user=getCookieUser(cookie);
        System.out.println(user+" is its user");
        cookie.setUsername(user);
        System.out.println(this.cookieService.getActiveSession(cookie).toString());
        return this.cookieService.getActiveSession(cookie);
    }
}
