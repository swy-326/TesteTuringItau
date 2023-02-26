package com.sungwon.testeturing.validator;

import com.sungwon.testeturing.model.dto.PixDTO;
import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class PixDTOValidator implements Validator {

    @Autowired
    private ContaService contaService;

    @Override
    public boolean supports(Class<?> aClass) {
        return PixDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors bindingResult) {

        PixDTO pixDTO = (PixDTO) o;

        // existe conta origem
        Optional<Conta> contaOrigemOptional = contaService.findById(pixDTO.getIdContaOrigem());
        Conta contaOrigem = null;
        if (contaOrigemOptional.isEmpty())
            bindingResult.rejectValue("idContaOrigem", null, "Conta origem inexistente");
        else
            contaOrigem = contaOrigemOptional.get();

        // existe conta destino
        Optional<Conta> contaDestinoOptioinal = contaService.findByChavePix(pixDTO.getChavePix());
        Conta contaDestino = null;
        if (contaDestinoOptioinal.isEmpty())
            bindingResult.rejectValue("chavePix", null,"Chave pix inexistente");
        else
            contaDestino = contaDestinoOptioinal.get();

        // origem e desitno nao sao iguais
        if (contaDestino != null && contaOrigem != null && contaDestino.equals(contaOrigem))
            bindingResult.rejectValue("chavePix", null, "Transferência não permitida para a mesma conta emissora");

        // valor esta entre 0 a 5k
        if (pixDTO.getValorTransacao().compareTo(BigDecimal.valueOf(0.01)) < 0
        &&  pixDTO.getValorTransacao().compareTo(BigDecimal.valueOf(5000.00)) > 0)
            bindingResult.rejectValue("valorTransacao", null, "Valor deve ser entre R$0,00 e R$5.000,00");
    }
}
