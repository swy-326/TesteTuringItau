package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.CadastroDTO;
import com.sungwon.testeturing.model.entity.Usuario;
import com.sungwon.testeturing.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value  = "/")
public class MainController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(){
        return "login/index";
    }

    @GetMapping("/home/main")
    public String home(){
        return "sucesso";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model){
        model.addAttribute("user", new CadastroDTO());
        return "cadastro/index";
    }

    @PostMapping("/cadastrando")
    public String processarCadastro(CadastroDTO cadastroDTO){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Usuario novoUsuario = cadastroDTO.toUsuario();
        String encodedPassword = passwordEncoder.encode(novoUsuario.getPassword());
        novoUsuario.setPassword(encodedPassword);

        usuarioService.save(novoUsuario);
        return "login/index"; // apos o cadastro, redireciona para login
    }

}
