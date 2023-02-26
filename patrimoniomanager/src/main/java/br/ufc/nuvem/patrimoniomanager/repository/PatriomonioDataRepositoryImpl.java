package br.ufc.nuvem.patrimoniomanager.repository;

import br.ufc.nuvem.patrimoniomanager.model.util.FileData;
import br.ufc.nuvem.patrimoniomanager.model.util.FileReference;
import br.ufc.nuvem.patrimoniomanager.strategy.StorageStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PatriomonioDataRepositoryImpl implements AppDataRepository {
    private final StorageStrategy s3StorageStrategy;

    @Override
    public FileData insertData(String foldername, String filename) {
        return s3StorageStrategy.generateUploadLink(FileReference.builder().folderName(foldername).filename(filename)
                .build());

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
