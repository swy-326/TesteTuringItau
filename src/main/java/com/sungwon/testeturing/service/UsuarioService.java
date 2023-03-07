package com.sungwon.testeturing.service;

import com.sungwon.testeturing.model.entity.Usuario;
import com.sungwon.testeturing.model.entity.UsuarioUser;
import com.sungwon.testeturing.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario save(Usuario usuario){
        return repository.save(usuario);
    }

    public Optional<Usuario> findById(String id){
        return repository.findById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username).get();
        if (usuario == null){
            throw new UsernameNotFoundException(username);
        }
        return new UsuarioUser(usuario);
    }
}
