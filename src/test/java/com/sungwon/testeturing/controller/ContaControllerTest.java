package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.repository.ContaRepository;
import com.sungwon.testeturing.model.repository.UsuarioRepository;
import com.sungwon.testeturing.service.ContaService;
import com.sungwon.testeturing.validator.ContaDTOValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ContaController.class)
@Import(ContaDTOValidator.class)
@AutoConfigureMockMvc(addFilters = false)
public class ContaControllerTest {

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

    @Test
    public void novaConta_deveNegarAcessoAnonimo() throws Exception {
        mvc.perform(get("/conta/nova"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "Yoon")
    @WithUserDetails
    public void novaConta_deveCriarNovaConta() throws Exception {

        mvc.perform(post("/conta/nova")
                .param("chavePix", "1")
                .param("nroBanco", "1")
                .param("nroAgencia", "1")
                .param("nroConta", "1"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    public void novaConta_naoDeveCriarNovaConta() throws Exception {
        mvc.perform(post("/conta/nova")
                        .param("chavePix", "a")
                        .param("nroBanco", "a")
                        .param("nroAgencia", "a")
                        .param("nroConta", ""))
                //.andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }



}
