package br.ufc.nuvem.patrimoniomanager.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class PatrimonioDataRepositoryMock implements PatrimonioDataRepository{


    @Override
    public String insertData(MultipartFile filename) {
        return filename.getOriginalFilename();
    }
}
