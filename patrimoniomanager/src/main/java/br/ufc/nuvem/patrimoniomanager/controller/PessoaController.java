package br.ufc.nuvem.patrimoniomanager.controller;

import br.ufc.nuvem.patrimoniomanager.model.DTO.PessoaDTO;
import br.ufc.nuvem.patrimoniomanager.model.Pessoa;
import br.ufc.nuvem.patrimoniomanager.service.PessoaService;
import br.ufc.nuvem.patrimoniomanager.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PessoaController {
    private final PessoaService pessoaService;
    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<Pessoa> insertPessoa(@RequestParam PessoaDTO pessoaDTO) {
        return new ResponseEntity<>(pessoaService.save(pessoaDTO.toPessoa()), HttpStatus.ACCEPTED);
    }
}
