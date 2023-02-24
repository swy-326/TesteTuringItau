package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.UsuarioDTO;
import com.sungwon.testeturing.model.entity.Usuario;
import com.sungwon.testeturing.service.UsuarioService;
import com.sungwon.testeturing.validator.UsuarioDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;


@Controller
@RequestMapping(value  = "/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDTOValidator usuarioDTOValidator;

    @GetMapping
    public String cadastro(Model model){
        model.addAttribute("user", new UsuarioDTO());
        return "cadastro/index";
    }

    @PostMapping
    public String processarCadastro(@ModelAttribute("user") @Valid UsuarioDTO usuarioDTO, BindingResult bindingResult){

        usuarioDTOValidator.validate(usuarioDTO, bindingResult);
        if (bindingResult.hasErrors())
            return "cadastro/index";


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Usuario novoUsuario = usuarioDTO.toUsuarioEntity();
        String encodedPassword = passwordEncoder.encode(novoUsuario.getPassword());
        novoUsuario.setPassword(encodedPassword);

        usuarioService.save(novoUsuario);
        return "redirect:login/index";
    }

}
