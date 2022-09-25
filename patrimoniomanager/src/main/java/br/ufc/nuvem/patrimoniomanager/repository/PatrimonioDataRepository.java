package br.ufc.nuvem.patrimoniomanager.repository;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface PatrimonioDataRepository {
    public String insertData(String foldername, MultipartFile filename);

    public String getBemUrl(String folderfilename);

    public boolean deleteData(String foldernamefilename);

}
