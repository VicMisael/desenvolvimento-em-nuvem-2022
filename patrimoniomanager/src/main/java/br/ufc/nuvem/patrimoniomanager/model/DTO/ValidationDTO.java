package br.ufc.nuvem.patrimoniomanager.model.DTO;

import br.ufc.nuvem.patrimoniomanager.model.Validation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationDTO {
    Long idBem;
    String comentarios;
    Boolean aprovacao;

    public Validation toValidation() {
        return Validation.builder().aprovacao(aprovacao).idBem(idBem).comentarios(comentarios).date(new Date()).build();
    }
}
