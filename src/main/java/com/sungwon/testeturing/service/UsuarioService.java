package com.sungwon.testeturing.service;

import com.sungwon.testeturing.model.entity.Usuario;
import com.sungwon.testeturing.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario save(Usuario usuario){
        return repository.save(usuario);
    }

    public Optional<Usuario> findById(String id){
        return repository.findById(id);
    }

}
