package br.ufc.nuvem.patrimoniomanager.model.util;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class FileData {
    private  final String filename;
    private final String presignedLink;
}
