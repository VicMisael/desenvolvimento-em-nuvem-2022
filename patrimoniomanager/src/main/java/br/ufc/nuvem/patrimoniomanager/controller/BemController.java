package br.ufc.nuvem.patrimoniomanager.controller;


import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.DTO.BemDTO;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import br.ufc.nuvem.patrimoniomanager.service.BemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController("/bem")
@RequiredArgsConstructor
public class BemController {
    private final BemService bemService;

    @PostMapping
    public ResponseEntity<Bem> insertBem(@RequestParam BemDTO bemDTO) {
        return new ResponseEntity<>(bemService.save(bemDTO.toBem()), HttpStatus.ACCEPTED);
    }

    @PutMapping("/addfiles")
    public ResponseEntity<Bem> insertImageBem(@RequestParam Long id, @RequestParam MultipartFile file) {
        return new ResponseEntity<>(bemService.saveFile(id, file), HttpStatus.ACCEPTED);
    }

    ;

    @DeleteMapping
    public void deleteBem(@RequestParam Long id) {
        bemService.delete(id);
    }


}
