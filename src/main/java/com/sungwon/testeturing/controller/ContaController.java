package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.ContaDTO;
import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.security.CustomUserDetails;
import com.sungwon.testeturing.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping(value = "/nova")
    public String novaConta(Model model){
        model.addAttribute("conta", new ContaDTO());
        return "conta/nova_conta";
    }

    @PostMapping(value =  "/nova")
    public String processandoNovaConta(ContaDTO contaDTO, @AuthenticationPrincipal CustomUserDetails userDetails){

        Conta novaConta = contaDTO.toContaEntity();
        novaConta.setUsuarioRef(userDetails.getUsuario());
        novaConta.setSaldo(BigDecimal.ZERO);

        contaService.insert(novaConta);

        // atribuir usuarioRef
        // sado = 0
        return "redirect:/";
    }

    // mostrar saldo
}
