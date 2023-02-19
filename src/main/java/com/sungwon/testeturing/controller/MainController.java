package com.sungwon.testeturing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value  = "/")
public class MainController {

    // show name, accounts, balance
    @GetMapping("")
    public String home(){
        return "home/index";
    }

}
