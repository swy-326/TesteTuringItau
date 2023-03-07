package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.ContaDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.ui.Model;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ContaControllerUnitTest {

    @InjectMocks
    private ContaController contaController;

    @Mock
    private Model model;

    @Test
    public void novaConta(){
        String result = contaController.novaConta(model);

        verify(model, times(1)).addAttribute("conta", new ContaDTO());
        Assert.assertEquals("conta/nova_conta", result);
    }
}
