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
public class TedDocDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idContaOrigem;

    private String nroBanco;
    private String nroAgencia;
    private String nroConta;

    private TipoTransacao tipoTransacao;

    private BigDecimal valorTransacao;

}
