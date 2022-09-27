package br.ufc.nuvem.patrimoniomanager.model.DTO;

import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import lombok.Data;

@Data
public class BemEditDTO {

    Long idbem;
    Long usuarioid;
    String nome;
    String localizacao;
    String codpatrimonio;

    public Bem toBem() {
        return Bem.builder().idBem(idbem).usuario(Usuario.builder().codigoUsuario(usuarioid).build()).name(nome).localizacao(localizacao).codPatrimonio(codpatrimonio).build();
    }
}
