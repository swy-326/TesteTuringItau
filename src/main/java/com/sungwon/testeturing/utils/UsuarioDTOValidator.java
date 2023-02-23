package com.sungwon.testeturing.utils;

import com.sungwon.testeturing.model.dto.UsuarioDTO;
import com.sungwon.testeturing.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Slf4j
@Component
public class UsuarioDTOValidator implements Validator {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UsuarioDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors bindingResult) {

        UsuarioDTO usuarioDTO = (UsuarioDTO) o;

        usuarioDTO.setUsername(usuarioDTO.getUsername().replaceAll("\\D+",""));

        if (usuarioDTO.getUsername().isEmpty())
            bindingResult.rejectValue( "username", "deve conter CPF ou CNPJ");

        if (!(usuarioService.findById(usuarioDTO.getUsername()).isEmpty()))
            bindingResult.rejectValue("username", "usuario existente");

        if (usuarioDTO.getPassword().length() != 8)
            bindingResult.rejectValue("password", "senha deve conter 8 digitos");

        if (!verificarSenhaValdia(usuarioDTO.getPassword()))
            bindingResult.rejectValue("password", "senha deve conter caracteres especiais, letras maiúsculas e minúsculas");
    }

    private boolean verificarSenhaValdia(String senha){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[-+_!@#$%^&*., ?]).+$";
        Pattern p = Pattern.compile(regex);
        return p.matcher(senha).matches();
    }
}
