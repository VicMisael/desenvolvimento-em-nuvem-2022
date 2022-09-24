package br.ufc.nuvem.patrimoniomanager.repository;

import br.ufc.nuvem.patrimoniomanager.strategy.S3StorageStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
@RequiredArgsConstructor
public class PatriomonioDataRepositoryImpl implements PatrimonioDataRepository{
    S3StorageStrategy s3StorageStrategy;

    @Override
    public String insertData(String foldername, MultipartFile filename) {
        return null;
    }

    @Override
    public String deleteData(String foldername, String filename) {
        return null;
    }
}
