package com.sungwon.testeturing.validator;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PixDTOValidatorTest {

    @InjectMocks
    private PixDTOValidator pixDTOValidator;

    @Test
    public void novoPix_dadosValidos(){

    }

    @Test
    public void novoPix_contaOrigemInexistente_deveAdicionarErro(){

    }

    @Test
    public void novoPix_contaDestinoInexistente_deveAdicionarErro(){

    }

    @Test
    public void novoPix_contaOrigemDestinoIguais_deveAdicionarErro(){

    }

    @Test
    public void novoPix_valorNaoEntre0k5k_deveAdicionarErro(){

    }

}
