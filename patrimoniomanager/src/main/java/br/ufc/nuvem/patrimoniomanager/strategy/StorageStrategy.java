package br.ufc.nuvem.patrimoniomanager.strategy;

import org.springframework.web.multipart.MultipartFile;


public interface StorageStrategy {
    public String insertFileAtFolder(String foldername, MultipartFile file);

    public boolean deleteFile(String filename);
    public String getUrl(String filename);

}
