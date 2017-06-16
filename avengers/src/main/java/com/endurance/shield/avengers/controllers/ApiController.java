package com.endurance.shield.avengers.controllers;

import com.endurance.shield.avengers.utils.KeyManager;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kai on 16/6/17.
 */
@RestController
public class ApiController {

    @Autowired
    private Environment env;

    @RequestMapping(value = "/checkUser/{username}",method = RequestMethod.GET)
    public void checkUser(@PathVariable String username, HttpServletResponse servletResponse)
    {
        System.out.println(username);
        try{
            HttpResponse<JsonNode> jsonResponse = Unirest.get("http://127.0.0.1:6969/checkUser")
                    .header("accept", "application/json")
                    .queryString("username", username)
                    .asJson();
            servletResponse.setStatus(jsonResponse.getStatus());
        }
        catch (UnirestException e)
        {
            servletResponse.setStatus(400);
        }
    }
    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    public void registerUser(@RequestBody Map<String,String> userDetails, HttpServletResponse servletResponse)
    {
        String squad = env.getProperty("app.name");
        userDetails.put("squad",squad);
        try{
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://127.0.0.1:6969/createUser")
                    .header("accept", "application/json")
                    .header("Content-Type","application/json")
                    .body(new JSONObject(userDetails))
                    .asJson();
            System.out.println(jsonResponse.getStatus());
            servletResponse.setStatus(jsonResponse.getStatus());
        }
        catch (UnirestException e)
        {
            servletResponse.setStatus(400);
        }
    }

    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public void loginUser(@RequestBody Map<String,String> userDetails, HttpServletResponse servletResponse)
    {
        try{
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://127.0.0.1:6969/loginUser")
                    .header("accept", "application/json")
                    .header("Content-Type","application/json")
                    .body(new JSONObject(userDetails))
                    .asJson();
            System.out.println(jsonResponse.getStatus());
            servletResponse.setStatus(jsonResponse.getStatus());
            if(jsonResponse.getStatus() == 200)
            {
                userDetails.put("time", String.valueOf(System.currentTimeMillis()));
                String token = KeyManager.encrypt(this.env.getProperty("secret.key"),userDetails);
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                cookie.setMaxAge(265 * 24 * 60 * 60);
                servletResponse.addCookie(cookie);
                Map<String,String> cookieMap = new HashMap<>();
                cookieMap.put("cookie",token);
                jsonResponse = Unirest.post("http://127.0.0.1:6969/createCookie")
                        .header("accept", "application/json")
                        .header("Content-Type","application/json")
                        .body(new JSONObject(cookieMap))
                        .asJson();
                System.out.println(jsonResponse.getStatus());
                //TODO: If failed, return server N/A
            }
        }
        catch (UnirestException e)
        {
            servletResponse.setStatus(400);
        }
    }


}
