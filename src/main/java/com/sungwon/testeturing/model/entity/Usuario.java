package com.sungwon.testeturing.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @Column(name="username", nullable = false, unique = true)
    private String username; // cnpj ou cpf

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nomeCompleto;
}
