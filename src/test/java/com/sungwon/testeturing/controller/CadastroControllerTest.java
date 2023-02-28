package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.repository.UsuarioRepository;
import com.sungwon.testeturing.service.UsuarioService;
import com.sungwon.testeturing.validator.UsuarioDTOValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CadastroController.class)
@Import(UsuarioDTOValidator.class)
@AutoConfigureMockMvc
public class CadastroControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void cadastro_permitirAcessoAnonimo() throws Exception {
        mvc.perform(get("/cadastro"))
                .andExpect(status().isOk())
                .andExpect(view().name("cadastro/index"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void processarCadastro_deveCriarUsuario() throws Exception {
        mvc.perform(post("/cadastro")
                .param("username", "1")
                .param("password", "09POpo**")
                .param("nomeCompleto", "a"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    public void processarCadastro_naoDeveCriarUsuario() throws Exception {
        mvc.perform(post("/cadastro")
                .param("username", "aaa")
                .param("password", "0000")
                .param("nomeCompleto", "a"))
            .andExpect(status().isOk())
            .andExpect(view().name("cadastro/index"));
    }

}
