package com.sungwon.testeturing.utils;

import com.sungwon.testeturing.model.dto.ContaDTO;
import com.sungwon.testeturing.model.dto.UsuarioDTO;
import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.service.ContaService;
import com.sungwon.testeturing.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class ContaDTOValidator implements Validator {

    @Autowired
    private ContaService contaService;

    @Override
    public boolean supports(Class<?> aClass) {
        return ContaDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors bindingResult) {

        ContaDTO contaDTO = (ContaDTO) o;

        if (contaService.findByChavePix(contaDTO.getChavePix()).isPresent())
            bindingResult.rejectValue("chavePix", "chave pix ja existente");

        if (contaService.findByBancoAgenciaConta(
                contaDTO.getNroBanco(), contaDTO.getNroAgencia(), contaDTO.getNroConta()
        ).isPresent())
            bindingResult.rejectValue("nroConta", "conta ja existente");

    }
}
