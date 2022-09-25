package br.ufc.nuvem.patrimoniomanager.repository;

import br.ufc.nuvem.patrimoniomanager.strategy.S3StorageStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Repository
@RequiredArgsConstructor
public class PatriomonioDataRepositoryImpl implements PatrimonioDataRepository {
    S3StorageStrategy s3StorageStrategy;

    @Override
    public String insertData(String foldername, MultipartFile file) {
        return s3StorageStrategy.insertFileAtFolder(foldername, file);
    }

    @Override
    public String getBemUrl(String foldernamefilename) {
        return s3StorageStrategy.getUrl(foldernamefilename);
    }

    @Override
    public boolean deleteData(String foldernamefilename) {
        return s3StorageStrategy.deleteFile(foldernamefilename);
    }
}
