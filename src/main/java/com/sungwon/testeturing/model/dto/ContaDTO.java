package com.sungwon.testeturing.model.dto;

import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Usuario usuarioRef;

    private String chavePix;
    private String nroBanco;
    private String nroAgencia;
    private String nroConta;
    private BigDecimal saldo;

    public static ContaDTO create(Conta conta){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(conta, ContaDTO.class);
    }

    public Conta toContaEntity(){
        Conta conta = new Conta();

        conta.setUsuarioRef(this.getUsuarioRef());
        conta.setChavePix(this.getChavePix());
        conta.setNroBanco(this.getNroBanco());
        conta.setNroAgencia(this.getNroAgencia());
        conta.setNroConta(this.getNroConta());
        conta.setSaldo(this.getSaldo());

        return conta;
    }

}
