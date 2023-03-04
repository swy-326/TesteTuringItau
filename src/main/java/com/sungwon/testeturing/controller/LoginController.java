package com.sungwon.testeturing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value = "/login")
public class LoginController {

    @GetMapping
    public String login(){
        log.info("Carregando pagina de login");
        return "login/index";
    }

}
