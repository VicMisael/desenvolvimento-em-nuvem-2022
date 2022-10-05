package br.ufc.nuvem.patrimoniomanager.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BemEditDTO {

    @NonNull
    Long codbem;
    @NonNull
    String nome;
    @NonNull
    String localizacao;

}
