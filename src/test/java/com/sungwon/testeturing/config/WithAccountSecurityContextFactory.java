package com.sungwon.testeturing.config;

import com.sungwon.testeturing.model.entity.Usuario;
import com.sungwon.testeturing.model.entity.UsuarioUser;
import com.sungwon.testeturing.security.CustomUserDetails;
import com.sungwon.testeturing.service.ContaService;
import com.sungwon.testeturing.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

@RequiredArgsConstructor
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public SecurityContext createSecurityContext(WithAccount withAccount) {

        String username = "000";
        String password = "09POpo**";
        String nomeCompleto = "Nome Completo";

        Usuario usuario = new Usuario(username, password, nomeCompleto);

        usuarioService.save(usuario);

        UserDetails principal = usuarioService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                principal.getPassword(),
                principal.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);

        return securityContext;
    }
}