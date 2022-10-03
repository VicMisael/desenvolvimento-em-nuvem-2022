package br.ufc.nuvem.patrimoniomanager.controller;


import br.ufc.nuvem.patrimoniomanager.configuration.security.UserDetailsImpl;
import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.DTO.BemDTO;
import br.ufc.nuvem.patrimoniomanager.model.DTO.BemEditDTO;
import br.ufc.nuvem.patrimoniomanager.model.Role;
import br.ufc.nuvem.patrimoniomanager.service.BemService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static br.ufc.nuvem.patrimoniomanager.configuration.security.SecurityUtils.containsAuthority;

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
    @ApiOperation("editar e associar arquivo a bem")
    public ResponseEntity<Bem> insertImageBem(@RequestParam Long id, @RequestParam MultipartFile file) {
        return new ResponseEntity<>(bemService.addFile(id, file), HttpStatus.ACCEPTED);
    }

    @GetMapping()
    @ApiOperation("Get bens by name")
    public ResponseEntity<List<Bem>> getBensByName(@RequestParam(value = "name", required = false) Optional<String> name,
                                                   @RequestParam(value = "localizacao", required = false) Optional<String> localizacao) {
        //Puxar o principal, Se o usuario for root pega todos, se for User pega só os deles
        //return new ResponseEntity<>(bemService.searchBensByName(name), HttpStatus.ACCEPTED);

        SecurityContext context = SecurityContextHolder.getContext();
        Long codUsuario;
        if (containsAuthority(context, Role.USER)) {
            codUsuario = ((UserDetailsImpl) context.getAuthentication().getPrincipal()).getUsuario().getCodigoUsuario();
            if (localizacao.isPresent() && name.isPresent()) {
                return new ResponseEntity<>(bemService.searchBensByLocalizationAndName(Optional.ofNullable(codUsuario), name.get(), localizacao.get()), HttpStatus.ACCEPTED);
            } else if (localizacao.isEmpty() && name.isPresent()) {
                return new ResponseEntity<>(bemService.searchBensByName(Optional.ofNullable(codUsuario), name.get()), HttpStatus.ACCEPTED);
            } else if (localizacao.isPresent()) {
                return new ResponseEntity<>(bemService.searchBensWithLocalization(Optional.ofNullable(codUsuario), localizacao.get()), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(bemService.userBensList(codUsuario), HttpStatus.ACCEPTED);
        }
        if (localizacao.isPresent() && name.isPresent()) {
            return new ResponseEntity<>(bemService.searchBensByLocalizationAndName(Optional.empty(), name.get(), localizacao.get()), HttpStatus.ACCEPTED);
        } else if (localizacao.isEmpty() && name.isPresent()) {
            return new ResponseEntity<>(bemService.searchBensByName(Optional.empty(), name.get()), HttpStatus.ACCEPTED);
        } else if (localizacao.isPresent()) {
            return new ResponseEntity<>(bemService.searchBensWithLocalization(Optional.empty(), localizacao.get()), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(bemService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bem>> getBem(@PathVariable Long id) {
        return new ResponseEntity<>(bemService.findBemById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar Bem")
    public void deleteBem(@PathVariable Long id) {
        bemService.delete(id);
    }


}
