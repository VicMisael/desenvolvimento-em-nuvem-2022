package br.ufc.nuvem.patrimoniomanager.model.DTO;

import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsuarioDTO {

    @JsonProperty("name")
    String nome;
    public Usuario toUsuario(){
      return Usuario.builder().nome(nome).build();
    }
}

