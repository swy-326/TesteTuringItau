package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.ContaDTO;
import com.sungwon.testeturing.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/conta")
public class ContaController {

    /*
    // abrir nova conta
    @GetMapping(value = "/nova")
    public String novaConta(){
        return "";
    }

    @PostMapping(value =  "/processando")
    public String processandoNovaConta(@AuthenticationPrincipal CustomUserDetails userDetails, ContaDTO contaDTO){

        // receber userId do front ou extrair aqui de java msm

    }
    */

    // mostrar saldo
}
