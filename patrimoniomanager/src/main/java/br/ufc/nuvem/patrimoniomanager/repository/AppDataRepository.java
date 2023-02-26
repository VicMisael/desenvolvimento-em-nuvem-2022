package br.ufc.nuvem.patrimoniomanager.repository;


import br.ufc.nuvem.patrimoniomanager.model.util.FileData;

public interface AppDataRepository {
     FileData insertData(String foldername, String filename);
     String getBemUrl(String folderfilename);
     boolean deleteData(String foldernamefilename);

}
