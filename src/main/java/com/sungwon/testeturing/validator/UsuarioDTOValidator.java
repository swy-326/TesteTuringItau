package com.sungwon.testeturing.validator;

import com.sungwon.testeturing.model.dto.UsuarioDTO;
import com.sungwon.testeturing.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

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
            bindingResult.rejectValue("username", null, "O campo não pode ser vazio");

        if (!usuarioDTO.getUsername().matches("^\\d+$"))
            bindingResult.rejectValue("username", null, "CPF ou CNPJ deve conter somente números");

        if (usuarioService.findById(usuarioDTO.getUsername()).isPresent())
            bindingResult.rejectValue("username", null, "Usuário já existente");

        if (usuarioDTO.getPassword().length() != 8)
            bindingResult.rejectValue("password", null, "A senha deve conter 8 dígitos");

        if (!verificarSenhaValida(usuarioDTO.getPassword()))
            bindingResult.rejectValue("password", null, "A senha deve conter caracteres especiais, letras maiusculas e minusculas");
    }

    private boolean verificarSenhaValida(String senha){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[-+_!@#$%^&*., ?]).+$";
        Pattern p = Pattern.compile(regex);
        return p.matcher(senha).matches();
    }
}
