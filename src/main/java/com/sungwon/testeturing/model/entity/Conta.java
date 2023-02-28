package com.sungwon.testeturing.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity@AllArgsConstructor

@Table(name = "tb_conta")
public class Conta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_ref", nullable = false)
    private Usuario usuarioRef;

    @Column(unique = true, nullable = false)
    private String chavePix;

    @Column(nullable = false)
    private String nroBanco;

    @Column(nullable = false)
    private String nroAgencia;

    @Column(nullable = false)
    private String nroConta;

    private BigDecimal saldo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return (Objects.equals(nroBanco, conta.nroBanco) && Objects.equals(nroAgencia, conta.nroAgencia) && Objects.equals(nroConta, conta.nroConta))
            || (Objects.equals(chavePix, conta.chavePix));
    }
}
