package br.ufc.nuvem.patrimoniomanager.model.DTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class BemEditDTO {

    @NonNull
    Long codbem;
    @NonNull
    String nome;
    @NonNull
    String localizacao;

}
