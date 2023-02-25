package com.sungwon.testeturing.service;

import com.sungwon.testeturing.model.entity.Usuario;
import com.sungwon.testeturing.model.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save_salvarUsuarioValido_returnUsuario(){
        Usuario usuario = new Usuario("1", "1", "1");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioService.save(usuario);

        Assert.assertEquals(resultado.getUsername(), usuario.getUsername());
    }

    @Test
    public void findById_buscarUsuarioValido_returnUsuario(){
        when(usuarioRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(new Usuario()));

        Optional<Usuario> resultado = usuarioService.findById("1");

        Assert.assertTrue(resultado.isPresent());
    }

    @Test
    public void findById_buscarUsuarioInexistente_returnNullable(){
        when(usuarioRepository.findById(any(String.class))).thenReturn(Optional.empty());

        Optional<Usuario> resultado = usuarioService.findById("1");

        Assert.assertTrue(resultado.isEmpty());
    }
}
