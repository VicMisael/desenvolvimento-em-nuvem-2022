package br.ufc.nuvem.patrimoniomanager.model;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Validation {
    Long idBem;
    String comentarios;
    Boolean aprovacao;
    Date date;
}
