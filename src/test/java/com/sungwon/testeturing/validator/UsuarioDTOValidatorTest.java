package com.sungwon.testeturing.validator;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UsuarioDTOValidatorTest {

    @InjectMocks
    private UsuarioDTOValidator usuarioDTOValidator;


    @Test
    public void cadastroUsuario_usernameValido(){

    }

    @Test
    public void cadastroUsuario_senhaValida(){

    }

    @Test
    public void cadastroUsuario_usernameVazio_deveAdicionarErroEmBindingResult(){

    }

    @Test
    public void cadastroUsuario_usernameInvalido_deveAdicionarErroEmBindingResult(){

    }

    @Test
    public void cadastroUsuario_usernameExistente_deveAdicionarErroEmBindingResult(){

    }

    @Test
    public void cadastroUsuario_senhaLarguraNao8_deveAdicionarErroEmBindingResult(){

    }

    @Test
    public void cadastroUsuario_senhaNaoContemUpperLowerCharEspeciais_deveAdicionarErroEmBindingResult(){

    }


}
