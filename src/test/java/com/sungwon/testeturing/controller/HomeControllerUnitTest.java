package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.config.WithAccount;
import com.sungwon.testeturing.model.dto.ContaDTO;
import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.entity.Usuario;
import com.sungwon.testeturing.security.CustomUserDetails;
import com.sungwon.testeturing.service.ContaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class HomeControllerUnitTest {

    @Mock
    private ContaService contaService;

    private HomeController homeController;

    private Iterator<Conta> contaIterator;

    @Mock
    private Model model;

    @BeforeEach
    void setup(){
        homeController = new HomeController(contaService);
        contaIterator = mock(Iterator.class);
        when(contaIterator.hasNext()).thenReturn(true, true, true, false);
        when(contaIterator.next()).thenReturn(
                new Conta(1L, new Usuario(), "1", "1", "1", "1", BigDecimal.ZERO)
        );
    }

    @Test
    @WithAccount("000")
    public void homeShouldReturnAllContas(){

        Conta conta = new Conta(1L, new Usuario(), "1", "1", "1", "1", BigDecimal.ZERO);
        ContaDTO contaDto = new ContaDTO(1L, new Usuario(), "1", "1", "1", "1", BigDecimal.ZERO, "0");

        List<Conta> list = new ArrayList<Conta>(Arrays.asList(conta));
        List<Conta> listContaMock = Mockito.mock(List.class);
        Conta contaMock = Mockito.mock(Conta.class);

        when(contaService.findByUsuarioRef(anyString())).thenReturn(list);
        Mockito.doCallRealMethod().when(listContaMock.stream().map(ContaDTO::create)).collect(Collectors.toList());

        String result = homeController.home(model, new CustomUserDetails());
        verify(model, times(1)).addAttribute("listaContas", "lista").addAttribute("nomeCompleto", "nome");

        Assert.assertEquals( "home/index", result);
    }


}
