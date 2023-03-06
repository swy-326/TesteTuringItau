package com.sungwon.testeturing.validator;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TedDocDTOValidatorTest {

    @InjectMocks
    private TedDocDTOValidator tedDocDTOValidator;

    @Test
    public void novaTedDoc_valoresValidos(){

    }

    @Test
    public void novaTedDoc_naoExisteContaOrigem_deveAdicionarErro(){

    }

    @Test
    public void novaTedDoc_naoExisteContaDestino_deveAdicionarErro(){

    }

    @Test
    public void novaTedDoc_contaOrigemDestinoIguais_deveAdicionarErro(){

    }

    @Test
    public void novaTed_valorNaoEntre5k10k_deveAdicionarErro(){

    }

    @Test
    public void novaDoc_valorNaoAcimaDe10k_deveAdicionarErro(){

    }

}
