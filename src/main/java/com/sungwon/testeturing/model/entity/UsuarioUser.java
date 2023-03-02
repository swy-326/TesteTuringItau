package com.sungwon.testeturing.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;


public class UsuarioUser extends User {

    private Usuario usuario;

    public UsuarioUser(Usuario usuario){
        super(usuario.getUsername(), usuario.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.usuario = usuario;
    }

}
