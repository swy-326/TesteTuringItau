package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.config.WithAccount;
import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.repository.ContaRepository;
import com.sungwon.testeturing.model.repository.UsuarioRepository;
import com.sungwon.testeturing.security.CustomUserDetails;
import com.sungwon.testeturing.service.ContaService;
import com.sungwon.testeturing.service.UsuarioService;
import com.sungwon.testeturing.validator.ContaDTOValidator;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ContaController.class)
@Import(ContaDTOValidator.class)
@AutoConfigureMockMvc
public class ContaControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContaService contaService;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private ContaRepository contaRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private CustomUserDetails customUserDetails;

    @MockBean
    private UsuarioService usuarioService;

    @AfterEach
    public void afterEach(){
        usuarioRepository.deleteAll();
    }

    @Test
    @WithAnonymousUser
    public void novaConta_deveNegarAcessoAnonimo() throws Exception {
        mvc.perform(get("/conta/nova"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAccount("000")
    public void novaConta_deveCriarNovaConta() throws Exception {

        when(contaService.save(any(Conta.class))).thenReturn(new Conta());
        // when(customUserDetails.getUsuario()).thenReturn(new Usuario());

        mvc.perform(post("/conta/nova")
                .param("chavePix", "1")
                .param("nroBanco", "1")
                .param("nroAgencia", "1")
                .param("id", String.valueOf(1L))
                .param("nroConta", "1"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAccount("000")
    public void novaConta_naoDeveCriarNovaConta() throws Exception {
        mvc.perform(post("/conta/nova")
                        .param("chavePix", "a")
                        .param("nroBanco", "a")
                        .param("nroAgencia", "a")
                        .param("nroConta", ""))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

}
