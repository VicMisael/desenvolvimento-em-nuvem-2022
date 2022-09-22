package br.ufc.nuvem.patrimoniomanager.model.DTO;

import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class UsuarioDTO {

    @JsonProperty("name")
    @NonNull
    String nome;

    @JsonProperty("email")
    @NonNull
    String email;

    @JsonProperty("indentificacao")
    @NonNull
    String identificacao;

    @JsonProperty("senha")
    @NonNull
    String senha;

    public Usuario toUsuario() {
        String s3BucketName = (nome + identificacao) + (nome + identificacao).hashCode();
        return Usuario.builder().nome(nome).email(email).senha(senha).identificacao(identificacao).s3BucketName(s3BucketName).build();
    }
}

