package br.ufc.nuvem.patrimoniomanager.strategy;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Profile("docker")
public class MinIOStorageStrategy implements StorageStrategy {
    @Override
    public String insertFileAtFolder(String foldername, MultipartFile file) {
        return null;
    }

    @Override
    public boolean deleteFile(String filename) {
        return false;
    }

    @Override
    public String getUrl(String filename) {
        return null;
    }
}
