package br.ufc.nuvem.patrimoniomanager.controller;


import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.DTO.BemDTO;
import br.ufc.nuvem.patrimoniomanager.model.DTO.BemEditDTO;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import br.ufc.nuvem.patrimoniomanager.service.BemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bem")
@RequiredArgsConstructor

public class BemController {
    private final BemService bemService;

    @PostMapping()
    @ApiOperation("Inserir bem")
    public ResponseEntity<Bem> insertBem(@RequestBody BemDTO bemDTO) {
        return new ResponseEntity<>(bemService.save(bemDTO.toBem()), HttpStatus.ACCEPTED);
    }

    @PutMapping()
    @ApiOperation("Editar Bem")
    public ResponseEntity<Bem> editarBem(@RequestBody BemEditDTO bemDTO) {
        return new ResponseEntity<>(bemService.update(bemDTO.getCodbem(), bemDTO.toBem()), HttpStatus.ACCEPTED);
    }

    @PutMapping("/addfiles")
    @ApiOperation("Associar arquivo a bem")
    public ResponseEntity<Bem> insertImageBem(@RequestBody Long id, @RequestParam MultipartFile file) {
        return new ResponseEntity<>(bemService.saveFile(id, file), HttpStatus.ACCEPTED);
    }

    @GetMapping()
    @ApiOperation("Get bens by name")
    public ResponseEntity<List<Bem>> getBensByName(@RequestParam String name, @RequestParam String localizacaos) {
        //Puxar o principal, Se o usuario for root pega todos, se for User pega só os deles
        return new ResponseEntity<>(bemService.searchBensByName(name), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bem>> getBem(@PathVariable Long id) {
        return new ResponseEntity<>(bemService.findBemById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation("Get bens by name")
    public ResponseEntity<List<Bem>> getBensUsuario() {
        //Puxar o principal, Se o usuario for root pega todos, se for User pega só os deles
        return new ResponseEntity<>(bemService.userBensList(1L), HttpStatus.ACCEPTED);
    }

    @DeleteMapping()
    @ApiOperation("Deletar Bem")
    public void deleteBem(@RequestBody Long id) {
        bemService.delete(id);
    }


}
