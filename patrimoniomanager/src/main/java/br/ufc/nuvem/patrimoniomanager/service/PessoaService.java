package br.ufc.nuvem.patrimoniomanager.service;

import br.ufc.nuvem.patrimoniomanager.model.Pessoa;
import br.ufc.nuvem.patrimoniomanager.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PessoaService {

    private final S3Service s3Service;
    private final PessoaRepository pessoaRepository;

    public Pessoa save(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public void find(Long id){
        pessoaRepository.findById(id);
    }

    public void delete(Pessoa pessoa){
        pessoaRepository.delete(pessoa);
    };

    public void insertPhoto(Pessoa pessoa){

    }

}
