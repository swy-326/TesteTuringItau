package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.UsuarioDTO;
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
@RequestMapping(value  = "/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String cadastro(Model model){
        model.addAttribute("user", new UsuarioDTO());
        return "cadastro/index";
    }

    @PostMapping
    public String processarCadastro(UsuarioDTO UsuarioDTO){

        // se senha != len(8) e nao conter M m $ -> erro

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Usuario novoUsuario = UsuarioDTO.toUsuarioEntity();
        String encodedPassword = passwordEncoder.encode(novoUsuario.getPassword());
        novoUsuario.setPassword(encodedPassword);

        usuarioService.save(novoUsuario);
        return "login/index"; // apos o cadastro, redireciona para login
    }

}
