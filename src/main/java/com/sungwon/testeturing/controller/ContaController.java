package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.ContaDTO;
import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.security.CustomUserDetails;
import com.sungwon.testeturing.service.ContaService;
import com.sungwon.testeturing.validator.ContaDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

import java.math.BigDecimal;

@Controller
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaDTOValidator contaDTOValidator;

    @GetMapping(value = "/nova")
    public String novaConta(Model model){
        model.addAttribute("conta", new ContaDTO());
        return "conta/nova_conta";
    }

    @PostMapping(value =  "/nova")
    public String processandoNovaConta(@ModelAttribute("conta") @Valid ContaDTO contaDTO, BindingResult bindingResult,
                                       @AuthenticationPrincipal CustomUserDetails userDetails){

        contaDTOValidator.validate(contaDTO, bindingResult);
        if (bindingResult.hasErrors())
            return "conta/nova_conta";


        // criar nova conta e salvar no bd
        Conta novaConta = contaDTO.toContaEntity();
        novaConta.setUsuarioRef(userDetails.getUsuario());
        novaConta.setSaldo(BigDecimal.ZERO);

        contaService.save(novaConta);

        return "redirect:/";
    }
}
