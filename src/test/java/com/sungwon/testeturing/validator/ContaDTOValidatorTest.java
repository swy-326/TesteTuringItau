package com.sungwon.testeturing.validator;

import com.sungwon.testeturing.model.dto.ContaDTO;
import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.entity.Usuario;
import com.sungwon.testeturing.service.ContaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ContaDTOValidatorTest {

    @Mock
    private ContaService contaService;

    @InjectMocks
    private ContaDTOValidator contaDTOValidator;

    @Test
    public void novaConta_dadosValidos(){

    }

    @Test
    public void novaConta_chavePixInvalido_deveAdicionarErro(){

        ContaDTO contaDTO = new ContaDTO(1L, new Usuario(), "1", "1", "1", "1", BigDecimal.ZERO);
        Conta conta = new Conta(1L, new Usuario(), "1", "1", "1", "1", BigDecimal.ZERO);

        when(contaService.findByChavePix(any(String.class))).thenReturn(Optional.ofNullable(conta));

        Errors errors = new BeanPropertyBindingResult(contaDTO, "contaDTO");
        contaDTOValidator.validate(contaDTO, errors);

        Assert.assertFalse(errors.hasErrors());
    }

    @Test
    public void novaConta_nroBancoInvalido_deveAdicionarErro(){

    }

    @Test
    public void novaConta_nroAgenciaInvalido_deveAdicionarErro(){

    }

    @Test
    public void novaConta_nroContaInvalido_deveAdicionarErro(){

    }

    @Test
    public void novaConta_chavePixExistente_deveAdicionarErro(){

    }

    @Test
    public void novaConta_contaExistente_deveAdicionarErro(){

    }


}
