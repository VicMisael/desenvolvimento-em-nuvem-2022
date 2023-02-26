package br.ufc.nuvem.patrimoniomanager.model.util;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class FileReference {
    private final String folderName;
    private final String filename;

}
