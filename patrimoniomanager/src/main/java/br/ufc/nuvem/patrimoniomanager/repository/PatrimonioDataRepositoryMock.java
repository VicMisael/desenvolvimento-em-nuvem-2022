package br.ufc.nuvem.patrimoniomanager.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class PatrimonioDataRepositoryMock implements PatrimonioDataRepository{
    @Override
    public String insertData(String foldername, MultipartFile filename) {
        return "null";
    }

    @Override
    public String deleteData(String foldername, String filename) {
        return "null";
    }
}
