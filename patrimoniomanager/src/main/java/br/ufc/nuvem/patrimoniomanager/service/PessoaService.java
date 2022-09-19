package br.ufc.nuvem.patrimoniomanager.service;

import br.ufc.nuvem.patrimoniomanager.model.Pessoa;
import br.ufc.nuvem.patrimoniomanager.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PessoaService {

    private final S3Service s3Service;
    private final PessoaRepository pessoaRepository;

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> find(Long id) {
       return pessoaRepository.findById(id);
    }

    public List<Pessoa> find() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoaRepository.findAll().forEach(pessoas::add);
        return pessoas;
    }


    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Pessoa updatePessoa(Pessoa pessoa) {
        if (pessoaRepository.existsById(pessoa.getId())) {
            return pessoaRepository.save(pessoa);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update without ID");

    }

    public void insertPhoto(Pessoa pessoa) {

    }

}
