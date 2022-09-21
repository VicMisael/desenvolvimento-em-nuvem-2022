package br.ufc.nuvem.patrimoniomanager.model.DTO;

import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BemDTO {


    @JsonProperty("userId")
    Long usuarioId;

    @JsonProperty("nome")
    String nome;

    public Bem toBem() {
       return Bem.builder().usuario(Usuario.builder().codigoUsuario(usuarioId).build()).nome(nome).build();
    }
}
