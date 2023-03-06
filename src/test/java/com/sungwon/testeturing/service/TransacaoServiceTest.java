package com.sungwon.testeturing.service;

import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.entity.Transacao;
import com.sungwon.testeturing.model.enums.TipoTransacao;
import com.sungwon.testeturing.model.repository.TransacaoRepository;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save_salvarTransacaoValida_returnTransacao(){
        Transacao transacao = new Transacao(1L, new Conta(), new Conta(), TipoTransacao.TED, BigDecimal.ZERO);
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacao);

        Transacao resultado = transacaoService.save(transacao);

        Assert.assertEquals(transacao.getId(), resultado.getId());
    }

    @Test
    public void findByContaOrigem_buscarTransacoesAtreladas_returnLista(){
        List<Transacao> transacaoList = Arrays.asList(new Transacao());
        when(transacaoRepository.findByContaOrigem(any(String.class))).thenReturn(transacaoList);

        List<Transacao> resultado = transacaoService.findByContaOrigem("1");

        Assert.assertEquals(1, resultado.size());
    }

    @Test
    public void findByContaOrigem_buscarContaSemTransacoes_returnListaVazia(){
        when(transacaoRepository.findByContaOrigem(any(String.class))).thenReturn(new ArrayList<>());

        List<Transacao> resultado = transacaoService.findByContaOrigem("1");

        Assert.assertEquals(0, resultado.size());
    }
}
