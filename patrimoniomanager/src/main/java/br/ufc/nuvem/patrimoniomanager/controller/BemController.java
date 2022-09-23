package br.ufc.nuvem.patrimoniomanager.controller;


import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.DTO.BemDTO;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import br.ufc.nuvem.patrimoniomanager.service.BemService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping("/addfiles")
    @ApiOperation("Associar arquivo a bem")
    public ResponseEntity<Bem> insertImageBem(@RequestBody Long id, @RequestParam MultipartFile file) {
        return new ResponseEntity<>(bemService.saveFile(id, file), HttpStatus.ACCEPTED);
    }

    ;

    @DeleteMapping()
    @ApiOperation("Deletar Bem")
    public void deleteBem(@RequestBody Long id) {
        bemService.delete(id);
    }


}
