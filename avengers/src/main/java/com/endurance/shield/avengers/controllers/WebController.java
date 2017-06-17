package com.endurance.shield.avengers.controllers;

import com.endurance.shield.avengers.utils.KeyManager;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Contended;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kai on 16/6/17.
 */
@Controller
public class WebController {

    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String hello(@CookieValue(value = "token", defaultValue = "") String encrypted, HttpServletResponse servletResponse) {
        System.out.println("In Web controller for /");
        if(encrypted.equals(""))
            return "index";
        else
        {
            System.out.println(encrypted);
            Map<String,String> cookieDetails = new HashMap<>();
            cookieDetails.put("cookie",encrypted);
            try{
                HttpResponse<JsonNode> jsonResponse = Unirest.post("http://127.0.0.1:6969/checkCookie")
                        .header("accept", "application/json")
                        .header("Content-Type","application/json")
                        .body(new JSONObject(cookieDetails))
                        .asJson();
                if(jsonResponse.getStatus() == 200)
                {
                    return "home";
                }
                System.out.println(jsonResponse.getStatus());
                return "index";
            }
            catch (UnirestException e)
            {
                return "index";
            }
        }
    }
    @RequestMapping(value = "/logoutUser",method = RequestMethod.POST)
    public String logoutUser(@CookieValue(value = "token", defaultValue = "") String encrypted, HttpServletResponse servletResponse)
    {
        try{
            Map<String,String> cookieMap = new HashMap<>();
            cookieMap.put("cookie",encrypted);
            HttpResponse<JsonNode> jsonResponse = Unirest.delete("http://127.0.0.1:6969/deleteCookie")
                    .header("accept", "application/json")
                    .header("Content-Type","application/json")
                    .body(new JSONObject(cookieMap))
                    .asJson();
            System.out.println(jsonResponse.getStatus());
            servletResponse.setStatus(jsonResponse.getStatus());
            Cookie cookie = new Cookie("token", "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            servletResponse.addCookie(cookie);
            return "redirect:/";
        }
        catch (UnirestException e)
        {
            servletResponse.setStatus(400);
            return "redirect:/";
        }
    }
    @RequestMapping(value = "/dummyPage",method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:9001")
    public String dummyPage(HttpServletResponse servletResponse)
    {
        return "dummy";
    }
}
