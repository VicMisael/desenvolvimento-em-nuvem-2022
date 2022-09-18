package br.ufc.nuvem.patrimoniomanager.model.DTO;

import br.ufc.nuvem.patrimoniomanager.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PessoaDTO {

    @JsonProperty("name")
    String nome;
    public Pessoa toPessoa(){
      return Pessoa.builder().nome(nome).build();
    }
}

