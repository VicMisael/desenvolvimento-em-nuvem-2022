package br.ufc.nuvem.patrimoniomanager.model.DTO;

import br.ufc.nuvem.patrimoniomanager.model.Role;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;

import lombok.Data;
import lombok.NonNull;

@Data
public class UsuarioDTO {


    @NonNull
    String name;


    @NonNull
    String email;


    @NonNull
    String identificacao;


    @NonNull
    String senha;


    String role;


    public Usuario toUsuario() {
        String folderName = (name + identificacao) + (name + identificacao).hashCode();
        return Usuario.builder().nome(name).email(email).senha(senha).identificacao(identificacao).role(Role.valueOf("USER")).folderName(folderName).build();
    }
}

