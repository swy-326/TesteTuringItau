package com.sungwon.testeturing.service;

import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.entity.Usuario;
import com.sungwon.testeturing.model.repository.ContaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save_salvarContaValida_returnConta(){
        Conta conta = new Conta(1L, new Usuario(), "1", "1", "1", "1", BigDecimal.ZERO);
        when(contaRepository.save(any(Conta.class))).thenReturn(conta);

        Conta resultado = contaService.save(conta);

        Assert.assertEquals(conta.getChavePix(), resultado.getChavePix());
    }

    @Test
    public void findByUsuarioRef_buscarContasValidas_returnLista(){
        List<Conta> contaList = Arrays.asList(new Conta());
        when(contaRepository.findByUsuarioRef(any(String.class))).thenReturn(contaList);

        List<Conta> resultado = contaService.findByUsuarioRef("1");

        Assert.assertEquals(1, resultado.size());
    }

    @Test
    public void findByUsuarioRef_buscarContaInexistente_returnListaVazia(){
        when(contaRepository.findByUsuarioRef(any(String.class))).thenReturn(new ArrayList<>());

        List<Conta> resultado = contaService.findByUsuarioRef("1");

        Assert.assertEquals(0, resultado.size());
    }

    @Test
    public void findById_buscarContaValida_returnConta(){
        when(contaRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(new Conta()));

        Optional<Conta> resultado = contaService.findById(1L);

        Assert.assertTrue(resultado.isPresent());
    }

    @Test
    public void findById_buscarContaInexistente_returnNullable(){
        when(contaRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Optional<Conta> resultado = contaService.findById(1L);

        Assert.assertTrue(resultado.isEmpty());
    }

    @Test
    public void findByChavePix_buscarContaValida_returnConta(){
        when(contaRepository.findByChavePix(any(String.class))).thenReturn(Optional.ofNullable(new Conta()));

        Optional<Conta> resultado = contaService.findByChavePix("1");

        Assert.assertTrue(resultado.isPresent());
    }

    @Test
    public void findByChavePix_buscarContaInexistente_returnNullable(){
        when(contaRepository.findByChavePix(any(String.class))).thenReturn(Optional.empty());

        Optional<Conta> resultado = contaService.findByChavePix("1");

        Assert.assertTrue(resultado.isEmpty());
    }

    @Test
    public void update_contaValida_returnConta(){
        Conta conta = new Conta(1L, new Usuario(), "1", "1", "1", "1", BigDecimal.ZERO);
        when(contaRepository.save(any(Conta.class))).thenReturn(conta);

        Conta resultado = contaService.update(conta);

        Assert.assertEquals(conta.getChavePix(), resultado.getChavePix());
    }

    @Test
    public void findByBancoAgenciaConta_buscarContaValida_returnConta(){
        when(contaRepository.findByBancoAgenciaConta(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.ofNullable(new Conta()));

        Optional<Conta> resultado = contaService.findByBancoAgenciaConta("1", "1", "1");

        Assert.assertTrue(resultado.isPresent());
    }

    @Test
    public void findByBancoAgenciaConta_buscarContaInexistente_returnNullable(){
        when(contaRepository.findByBancoAgenciaConta(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        Optional<Conta> resultado = contaService.findByBancoAgenciaConta("1", "1", "1");

        Assert.assertTrue(resultado.isEmpty());
    }
}
