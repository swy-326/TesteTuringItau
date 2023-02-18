package com.sungwon.testeturing.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="username", nullable = false, unique = true)
    private String username; // cnpj ou cpf

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nomeCompleto;
}
