package com.sungwon.testeturing.service;

import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public Conta insert(Conta conta){
        Conta nova_conta = repository.save(conta);
        return nova_conta;
    }

    public List<Conta> findByUsuarioRef(String usuarioRef){
        return repository.findByUsuarioRef(usuarioRef);
    }

}
