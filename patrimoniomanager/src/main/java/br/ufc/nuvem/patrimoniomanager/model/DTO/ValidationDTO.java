package br.ufc.nuvem.patrimoniomanager.model.DTO;

import br.ufc.nuvem.patrimoniomanager.model.Validation;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationDTO {
    @NonNull
    Long idBem;
    @NonNull
    String comentarios;
    @NonNull
    Boolean aprovacao;

    public Validation toValidation() {
        return Validation.builder().aprovacao(aprovacao).idBem(String.valueOf(idBem)).comentarios(comentarios).date(new Date()).build();
    }
}
