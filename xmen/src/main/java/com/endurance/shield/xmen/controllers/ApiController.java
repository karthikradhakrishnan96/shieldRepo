package com.endurance.shield.xmen.controllers;

import com.endurance.shield.xmen.utils.KeyManager;
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
    @RequestMapping(value = "/fetchToDos",method = RequestMethod.GET)
    public String fetchToDos(@CookieValue(value = "token", defaultValue = "") String encrypted, HttpServletResponse servletResponse)
    {
        Map<String ,String> userDetails = KeyManager.decrypt(this.env.getProperty("secret.key"),encrypted);
        Map<String,String> cookieMap = new HashMap<>();
        cookieMap.put("cookie",encrypted);
        try
        {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://127.0.0.1:6969/checkCookie")
                    .header("accept", "application/json")
                    .header("Content-Type","application/json")
                    .body(new JSONObject(cookieMap))
                    .asJson();
            if(jsonResponse.getStatus() == 200)
            {
                 jsonResponse = Unirest.get("http://127.0.0.1:6969/getUserItem")
                        .header("accept", "application/json")
                        .header("Content-Type","application/json")
                        .queryString("username",userDetails.get("username"))
                        .asJson();
                 return jsonResponse.getBody().toString();
            }

        }
        catch (Exception e)
        {

        }
        return null;
    }

    @RequestMapping(value = "/verifyToken",method = RequestMethod.POST)
    public void verifyToken(@RequestBody Map<String,String> cookieDetails, HttpServletResponse servletResponse)
    {
        System.out.println("In verifyToken, cookie is "+cookieDetails.get("cookie"));
        System.out.println(cookieDetails);
        String cookie = cookieDetails.get("cookie");
        try{
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://127.0.0.1:6969/checkCookie")
                    .header("accept", "application/json")
                    .header("Content-Type","application/json")
                    .body(new JSONObject(cookieDetails))
                    .asJson();
            if(jsonResponse.getStatus() == 200)
            {
                //redirect
                Map<String,String> userDetails = KeyManager.decrypt(this.env.getProperty("secret.key"),cookie);
                jsonResponse = Unirest.get("http://127.0.0.1:6969/checkUser")
                        .header("accept", "application/json")
                        .queryString("username", userDetails.get("username"))
                        .asJson();
                String responseJSONString = jsonResponse.getBody().toString();
                System.out.println("Cookie verified in verify token");
                System.out.println(responseJSONString);
                System.out.println(jsonResponse);

                Map<String,String> user = KeyManager.jsonToMap(responseJSONString);
                String squad = user.get("squad");
                if(!squad.equals("BOTH"))
                {
                    servletResponse.setStatus(401);
                }
                else
                {
                    Cookie setCookie = new Cookie("token", cookie);
                    setCookie.setPath("/");
                    setCookie.setMaxAge(265 * 24 * 60 * 60);
                    servletResponse.addCookie(setCookie);
                }
                System.out.println(user);
            }
            else {
                servletResponse.setStatus(404);
            }
        }
        catch (UnirestException e)
        {

        }
        System.out.println("Verify token is returning"+servletResponse.getStatus());
    }
    @RequestMapping(value = "/becomeBoth",method = RequestMethod.POST)
    public void becomeBoth(@RequestBody Map<String,String> cookieMap, HttpServletResponse servletResponse){
        String cookie = cookieMap.get("cookie");
        Map<String,String> userDetails = KeyManager.decrypt(this.env.getProperty("secret.key"),cookie);
        Cookie setCookie = new Cookie("token", cookie);
        setCookie.setPath("/");
        System.out.println("'Become both has userDetails as: "+userDetails);
        setCookie.setMaxAge(265 * 24 * 60 * 60);
        servletResponse.addCookie(setCookie);
        System.out.println(userDetails.get("username"));
        System.out.println("Cookie in becomeBoth: "+cookie);
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://127.0.0.1:6969/makeBoth")
                    .header("accept", "application/json")
                    .field("username", userDetails.get("username"))
                    .asJson();
            System.out.println(jsonResponse);
            servletResponse.setStatus(jsonResponse.getStatus());
        } catch (UnirestException e) {
            servletResponse.setStatus(400);
        }

    }

    @RequestMapping(value = "/getAllBio",method = RequestMethod.GET)
    public String getAllBio(@CookieValue(value = "token", defaultValue = "") String encrypted, HttpServletResponse servletResponse)
    {
        System.out.println("In Get all bio");
        Map<String ,String> userDetails = KeyManager.decrypt(this.env.getProperty("secret.key"),encrypted);
        Map<String,String> cookieMap = new HashMap<>();
        cookieMap.put("cookie",encrypted);
        try
        {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://127.0.0.1:6969/checkCookie")
                    .header("accept", "application/json")
                    .header("Content-Type","application/json")
                    .body(new JSONObject(cookieMap))
                    .asJson();
            if(jsonResponse.getStatus() == 200)
            {
                jsonResponse = Unirest.get("http://127.0.0.1:6969/getAllBio")
                        .header("accept", "application/json")
                        .header("Content-Type","application/json")
                        .queryString("username",userDetails.get("username"))
                        .asJson();
                System.out.println("Returning "+jsonResponse.getBody().toString());
                return jsonResponse.getBody().toString();
            }
        }
        catch (Exception e)
        {

        }
        return null;
    }





}
