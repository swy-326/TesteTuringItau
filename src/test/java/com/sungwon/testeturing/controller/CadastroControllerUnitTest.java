package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.UsuarioDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CadastroControllerUnitTest {

    @InjectMocks
    private CadastroController cadastroController;

    @Mock
    private Model model;

    @Test
    public void cadastro(){
        String result = cadastroController.cadastro(model);

        verify(model, times(1)).addAttribute("user", new UsuarioDTO());
        Assert.assertEquals("cadastro/index", result);
    }


}
