package br.ufc.nuvem.patrimoniomanager.model.DTO;

import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import lombok.Data;

@Data
public class BemDTO {


    Long usuarioid;

    String nome;

    String localizacao;


    public Bem toBem() {
       return Bem.builder().usuario(Usuario.builder().codigoUsuario(usuarioid).build()).name(nome).localizacao(localizacao).build();
    }
}
