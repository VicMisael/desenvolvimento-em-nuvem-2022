package br.ufc.nuvem.patrimoniomanager.controller;

import br.ufc.nuvem.patrimoniomanager.model.DTO.PessoaDTO;
import br.ufc.nuvem.patrimoniomanager.model.Pessoa;
import br.ufc.nuvem.patrimoniomanager.service.PessoaService;
import br.ufc.nuvem.patrimoniomanager.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PessoaController {
    private final PessoaService pessoaService;
    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<Pessoa> insertPessoa(@RequestParam PessoaDTO pessoaDTO) {
        return new ResponseEntity<>(pessoaService.save(pessoaDTO.toPessoa()), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        return new ResponseEntity<>(pessoaService.find(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> getPessoa(@PathVariable Long id) {
        return new ResponseEntity<>(pessoaService.find(id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePessoa(@RequestParam Long id) {
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<Pessoa> updatePessoa(Pessoa pessoa) {
        return new ResponseEntity<>(pessoaService.updatePessoa(pessoa), HttpStatus.ACCEPTED);
    }
}
