package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.ContaDTO;
import com.sungwon.testeturing.security.CustomUserDetails;
import com.sungwon.testeturing.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value  = "/")
public class HomeController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){

        String usuarioRef = userDetails.getUsername();
        String usuarioNomeCompleto = userDetails.getUsuario().getNomeCompleto();

        List<ContaDTO> listaContas = contaService.findByUsuarioRef(usuarioRef)
                .stream().map(ContaDTO::create).collect(Collectors.toList());

        model.addAttribute("listaContas", listaContas);
        model.addAttribute("nomeCompleto", usuarioNomeCompleto);

        return "home/index";
    }

}
