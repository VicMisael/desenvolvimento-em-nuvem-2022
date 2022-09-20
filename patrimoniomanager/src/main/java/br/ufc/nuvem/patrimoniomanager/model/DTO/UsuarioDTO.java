package br.ufc.nuvem.patrimoniomanager.model.DTO;

import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UsuarioDTO {

    @JsonProperty("name")
    String nome;

    @JsonProperty("email")
    String email;

    @JsonProperty("indentificacao")
    String identificacao;

    @JsonProperty("senha")
    String senha;

    public Usuario toUsuario(){
      return Usuario.builder().nome(nome).email(email).senha(senha).identificacao(identificacao).build();
    }
}

