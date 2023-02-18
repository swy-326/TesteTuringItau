package com.sungwon.testeturing.service;

import com.sungwon.testeturing.model.entity.Usuario;
import com.sungwon.testeturing.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario save(Usuario usuario){
        return repository.save(usuario);
    }

}
