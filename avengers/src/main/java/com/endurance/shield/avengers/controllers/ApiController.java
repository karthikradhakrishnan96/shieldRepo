package com.endurance.shield.avengers.controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public void registerUser(@RequestBody Map<String,String> userDetails, HttpServletResponse servletResponse)
    {
        String squad = env.getProperty("app.name");
        userDetails.put("squad",squad);
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
}
