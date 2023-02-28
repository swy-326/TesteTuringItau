package com.sungwon.testeturing.model.dto;

import com.sungwon.testeturing.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username; // cpf ou cnpj
    private String password;
    private String nomeCompleto;

    public Usuario toUsuarioEntity(){
        Usuario usuario = new Usuario();

        usuario.setUsername(this.getUsername());
        usuario.setPassword(this.getPassword());
        usuario.setNomeCompleto(this.getNomeCompleto());

        return usuario;
    }

}
