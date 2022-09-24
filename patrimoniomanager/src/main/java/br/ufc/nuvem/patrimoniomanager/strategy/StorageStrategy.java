package br.ufc.nuvem.patrimoniomanager.strategy;

import java.io.File;

public interface StorageStrategy {
    public String insertFileAtFolder(String foldername, File file);

    public boolean deleteFile(String filename);

    public String getObjectReference(String filename);


}
