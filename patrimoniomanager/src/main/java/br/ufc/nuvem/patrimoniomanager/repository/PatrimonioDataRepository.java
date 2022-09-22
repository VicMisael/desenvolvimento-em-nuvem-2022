package br.ufc.nuvem.patrimoniomanager.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface PatrimonioDataRepository {
    public String insertData(MultipartFile filename);
    
}
