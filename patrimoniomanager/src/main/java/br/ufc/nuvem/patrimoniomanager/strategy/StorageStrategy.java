package br.ufc.nuvem.patrimoniomanager.strategy;

import java.io.File;

public interface StorageStrategy {
    public boolean insertFileAtFolder(String foldername, File file);

    public boolean deleteFile(String filename);

    public String getObjectReference(String filename);

    public String deleteObject(String object);
}
