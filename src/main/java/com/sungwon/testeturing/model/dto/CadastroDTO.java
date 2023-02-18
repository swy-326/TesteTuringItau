package com.sungwon.testeturing.model.dto;

import com.sungwon.testeturing.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public Usuario toUsuario(){

        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsername(this.getUsername());
        novoUsuario.setPassword(this.getPassword());

        return novoUsuario;
    }

}
