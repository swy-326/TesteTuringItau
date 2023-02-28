package com.sungwon.testeturing.model.dto;

import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Conta contaOrigem;
    private Conta contaDestino;

    private TipoTransacao tipoTransacao;
    private BigDecimal valorTransacao;

}
