package com.sungwon.testeturing.service;

import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public Conta insert(Conta conta){
        Conta nova_conta = repository.save(conta);
        return nova_conta;
    }

}
