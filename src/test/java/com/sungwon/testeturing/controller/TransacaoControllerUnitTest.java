package com.sungwon.testeturing.controller;


import com.sungwon.testeturing.model.dto.PixDTO;
import com.sungwon.testeturing.model.dto.TedDocDTO;
import com.sungwon.testeturing.security.CustomUserDetails;
import com.sungwon.testeturing.service.ContaService;
import com.sungwon.testeturing.service.TransacaoService;
import com.sungwon.testeturing.validator.PixDTOValidator;
import com.sungwon.testeturing.validator.TedDocDTOValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TransacaoControllerUnitTest {

    @Mock
    private TransacaoService transacaoService;

    @Mock
    private ContaService contaService;

    @Mock
    private PixDTOValidator pixDTOValidator;

    @Mock
    private TedDocDTOValidator tedDocDTOValidator;

    @InjectMocks
    private TransacaoController transacaoController;

    @Mock
    private Model model;

    @BeforeEach
    void setup(){
        transacaoController = new TransacaoController(
                transacaoService, contaService, pixDTOValidator, tedDocDTOValidator
        );
    }

    @Test
    public void novaTransacaoPix(){
        String resultado = transacaoController.novaTransacaoPix(model, 1L, new CustomUserDetails());

        verify(model, times(1)).addAttribute("transacao", new PixDTO());
        verify(model, times(1)).addAttribute("contaId", 1L);
        Assert.assertEquals("transacao/pix", resultado);
    }

    @Test
    public void novaTransacaoTed(){
        String resultado = transacaoController.novaTransacaoTed(model, 1L, new CustomUserDetails());

        verify(model, times(1)).addAttribute("transacao", new TedDocDTO());
        verify(model, times(1)).addAttribute("contaId", 1L);
        Assert.assertEquals("transacao/ted", resultado);
    }

    @Test
    public void novaTransacaoDoc(){
        String resultado = transacaoController.novaTransacaoDoc(model, 1L, new CustomUserDetails());

        verify(model, times(1)).addAttribute("transacao", new TedDocDTO());
        verify(model, times(1)).addAttribute("contaId", 1L);
        Assert.assertEquals("transacao/doc", resultado);
    }


    //TODO validar transacoes com dados validos e dados invalidos

}
