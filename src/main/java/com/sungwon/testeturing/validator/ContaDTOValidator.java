package com.sungwon.testeturing.validator;

import com.sungwon.testeturing.model.dto.ContaDTO;
import com.sungwon.testeturing.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
            bindingResult.rejectValue("chavePix", null, "Chave Pix já existente");

        if (contaService.findByBancoAgenciaConta(
                contaDTO.getNroBanco(), contaDTO.getNroAgencia(), contaDTO.getNroConta()
        ).isPresent())
            bindingResult.rejectValue("nroConta", null, "Conta já existente no banco e agência fornecidos");

        if (!contaDTO.getChavePix().matches("^\\d+$"))
            bindingResult.rejectValue("chavePix", null, "Chave Pix deve ser um número");

        if (!contaDTO.getNroBanco().matches("^\\d+$"))
            bindingResult.rejectValue("nroBanco", null, "Número do banco deve ser um número");

        if (!contaDTO.getNroAgencia().matches("^\\d+$"))
            bindingResult.rejectValue("nroAgencia", null, "Número de agencia deve ser um número");

        if (!contaDTO.getNroConta().matches("^\\d+$"))
            bindingResult.rejectValue("nroConta", null, "Número da conta deve ser um número");
    }
}
