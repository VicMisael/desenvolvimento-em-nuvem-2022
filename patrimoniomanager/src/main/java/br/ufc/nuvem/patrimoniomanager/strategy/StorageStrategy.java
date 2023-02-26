package br.ufc.nuvem.patrimoniomanager.strategy;

import br.ufc.nuvem.patrimoniomanager.model.util.FileData;
import br.ufc.nuvem.patrimoniomanager.model.util.FileReference;


public interface StorageStrategy {
    FileData generateUploadLink(FileReference fileReference);

    boolean deleteFile(String filename);

    String getUrl(String filename);

}
