package br.ufc.nuvem.patrimoniomanager.model.DTO;

import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BemDTO {


    Long usuarioid;

    @JsonProperty("nome")
    String nome;

    String localizacao;

    String codpatrimonio;

    public Bem toBem() {
       return Bem.builder().usuario(Usuario.builder().codigoUsuario(usuarioid).build()).name(nome).localizacao(localizacao).codPatrimonio(codpatrimonio).build();
    }
}
