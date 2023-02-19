package com.sungwon.testeturing.model.dto;

import com.sungwon.testeturing.model.entity.Transacao;
import com.sungwon.testeturing.model.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoNovaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nroBanco;
    private String nroAgencia;
    private String nroConta;

    private TipoTransacao tipoTransacao;
    private BigDecimal valorTransacao;

    public Transacao toTransacaoEntity(){
        Transacao transacao = new Transacao();

        transacao.setTipoTransacao(this.tipoTransacao);
        transacao.setValorTransacao(this.valorTransacao);

        return transacao;
    }

}
