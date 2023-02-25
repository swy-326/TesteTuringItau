package com.sungwon.testeturing.service;

import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public Conta save(Conta conta){
        return repository.save(conta);
    }

    public List<Conta> findByUsuarioRef(String usuarioRef){
        return repository.findByUsuarioRef(usuarioRef);
    }

    public Optional<Conta> findById(Long id){
        return repository.findById(id);
    }

    public Optional<Conta> findByChavePix(String chavePix){
        return repository.findByChavePix(chavePix);
    }

    public Conta update(Conta conta){
        return repository.save(conta);
    }

    public Optional<Conta> findByBancoAgenciaConta(String nroBanco, String nroAgencia, String nroConta){
        return repository.findByBancoAgenciaConta(nroBanco, nroAgencia, nroConta);
    }

}
