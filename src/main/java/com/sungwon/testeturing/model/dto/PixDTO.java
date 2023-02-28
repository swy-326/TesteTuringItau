package com.sungwon.testeturing.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PixDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idContaOrigem;
    private String chavePix;
    private BigDecimal valorTransacao;

}
