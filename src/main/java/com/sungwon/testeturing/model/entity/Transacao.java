package com.sungwon.testeturing.model.entity;

import com.sungwon.testeturing.model.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "tb_transacao")
public class Transacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private Conta contaDestino;

    private TipoTransacao tipoTransacao;
    private BigDecimal valorTransacao;
}
