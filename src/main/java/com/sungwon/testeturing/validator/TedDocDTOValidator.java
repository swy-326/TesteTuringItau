package com.sungwon.testeturing.validator;

import com.sungwon.testeturing.model.dto.TedDocDTO;
import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.enums.TipoTransacao;
import com.sungwon.testeturing.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TedDocDTOValidator implements Validator {

    @Autowired
    private ContaService contaService;

    @Override
    public boolean supports(Class<?> aClass) {
        return TedDocDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors bindingResult) {

        TedDocDTO tedDocDTO = (TedDocDTO) o;

        // existe conta origem
        Optional<Conta> contaOrigemOptional = contaService.findById(tedDocDTO.getIdContaOrigem());
        Conta contaOrigem = null;
        if (contaOrigemOptional.isEmpty())
            bindingResult.rejectValue("idContaOrigem", null, "Conta origem inexistente");
        else
            contaOrigem = contaOrigemOptional.get();

        // existe conta destino
        Optional<Conta> contaDestinoOptioinal = contaService.findByBancoAgenciaConta(
                tedDocDTO.getNroBanco(), tedDocDTO.getNroAgencia(), tedDocDTO.getNroConta()
        );
        Conta contaDestino = null;
        if (contaDestinoOptioinal.isEmpty())
            bindingResult.rejectValue("nroConta", null,"Conta inexistente");
        else
            contaDestino = contaDestinoOptioinal.get();

        // origem e desitno nao sao iguais
        if (contaDestino != null && contaOrigem != null && contaDestino.equals(contaOrigem))
            bindingResult.rejectValue("nroConta", null, "Transferência não permitida para a mesma conta emissora");

        // verificar o valor
        if (tedDocDTO.getTipoTransacao() == TipoTransacao.TED){
            if (tedDocDTO.getValorTransacao().compareTo(BigDecimal.valueOf(5000.00)) < 0
                && tedDocDTO.getValorTransacao().compareTo(BigDecimal.valueOf(10000.00)) > 0)
                bindingResult.rejectValue("valorTransacao", null, "Valor deve ser acima de R$5.000,00 até R$10.000,00");
        }
        else if (tedDocDTO.getTipoTransacao() == TipoTransacao.DOC) {
            if (tedDocDTO.getValorTransacao().compareTo(BigDecimal.valueOf(10000.00)) < 0)
                bindingResult.rejectValue("valorTransacao", null, "Valor deve ser acima de R$10.000,00");
        }
        else {
            bindingResult.rejectValue("nroConta", null, "Tipo inválido de transacao");
        }
    }
}
