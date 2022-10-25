package br.ufc.nuvem.patrimoniomanager.repository;

import br.ufc.nuvem.patrimoniomanager.strategy.StorageStrategy;
import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PatriomonioDataRepositoryImpl implements PatrimonioDataRepository {
    private final StorageStrategy s3StorageStrategy;

    static final List<String> allowedStrings = List.of("jpg", "jpeg", "png", "pdf");

    @Override
    public String insertData(String foldername, MultipartFile file) {
        if (allowedStrings.contains(Files.getFileExtension(Objects.requireNonNull(file.getOriginalFilename())))) {
            return s3StorageStrategy.insertFileAtFolder(foldername, file);
        } else {
            log.info("invalid file");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Extensão invalida");
        }
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
