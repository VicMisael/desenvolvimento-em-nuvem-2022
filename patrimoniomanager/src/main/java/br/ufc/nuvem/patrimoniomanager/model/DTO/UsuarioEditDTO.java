package br.ufc.nuvem.patrimoniomanager.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEditDTO {

    @NonNull
    Long id;

    @NonNull
    String name;

    @NonNull
    String email;


    @NonNull
    String identificacao;


    @NonNull
    String senha;

}

