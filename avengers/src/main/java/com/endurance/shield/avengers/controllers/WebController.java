package com.endurance.shield.avengers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Contended;

/**
 * Created by kai on 16/6/17.
 */
@Controller
public class WebController {

    @RequestMapping("/")
    public String hello() {
        return "index";
    }
}
