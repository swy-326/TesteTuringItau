package com.sungwon.testeturing.config;

import com.sungwon.testeturing.model.entity.Usuario;
import com.sungwon.testeturing.security.CustomUserDetails;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@TestConfiguration
public class SpringSecurityTestConfig {

    @Bean
    //@Primary
    public UserDetailsService userDetailsService() {
        Usuario usuario = new Usuario("000", "$2a$10$zS3iVJCAvtHHodAbyFlQqeGp7xdxtma/mhTu/nUXUt85MdioMCqCm", "yoon");

        CustomUserDetails customUsuario = new CustomUserDetails(usuario);

        return new InMemoryUserDetailsManager(customUsuario);
    }

}
