package com.sungwon.testeturing.service;

import com.sungwon.testeturing.model.entity.Transacao;
import com.sungwon.testeturing.model.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    public Transacao save(Transacao transacao){
        return repository.save(transacao);
    }

    public List<Transacao> findByContaOrigem(String contaOrigem){
        return repository.findByContaOrigem(contaOrigem);
    }

}
