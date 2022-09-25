package br.ufc.nuvem.patrimoniomanager.controller;


import br.ufc.nuvem.patrimoniomanager.configuration.security.SecurityUtils;
import br.ufc.nuvem.patrimoniomanager.configuration.security.UserDetailsImpl;
import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.DTO.BemDTO;
import br.ufc.nuvem.patrimoniomanager.model.DTO.BemEditDTO;
import br.ufc.nuvem.patrimoniomanager.model.Role;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import br.ufc.nuvem.patrimoniomanager.service.BemService;
import br.ufc.nuvem.patrimoniomanager.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final UsuarioService usuarioService;

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
    public ResponseEntity<Bem> insertImageBem(@RequestParam Long id, @RequestParam MultipartFile file) {
        return new ResponseEntity<>(bemService.saveFile(id, file), HttpStatus.ACCEPTED);
    }

    @GetMapping()
    @ApiOperation("Get bens by name")
    public ResponseEntity<List<Bem>> getBensByName(@RequestParam("name") String name, @RequestParam("local") String localizacaos) {
        //Puxar o principal, Se o usuario for root pega todos, se for User pega só os deles
        //return new ResponseEntity<>(bemService.searchBensByName(name), HttpStatus.ACCEPTED);

        SecurityContext context = SecurityContextHolder.getContext();
        Long codUsuario = null;
        if (containsAuthority(context, Role.USER)) {
            codUsuario = ((UserDetailsImpl) context.getAuthentication().getPrincipal()).getUsuario().getCodigoUsuario();
        }
        if (!localizacaos.isBlank() && !name.isBlank()) {
            return new ResponseEntity<>(bemService.searchBensAndNameAndLocalization(Optional.ofNullable(codUsuario), name, localizacaos), HttpStatus.ACCEPTED);
        } else if (localizacaos.isBlank() && !name.isBlank()) {
            return new ResponseEntity<>(bemService.searchBens(Optional.ofNullable(codUsuario), name), HttpStatus.ACCEPTED);
        } else if (!localizacaos.isBlank() && name.isBlank()) {
            return new ResponseEntity<>(bemService.searchBensAndLocalization(Optional.ofNullable(codUsuario), name), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<List<Bem>>(List.of(), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bem>> getBem(@PathVariable Long id) {
        return new ResponseEntity<>(bemService.findBemById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation("Get bens by name")
    public ResponseEntity<List<Bem>> getBensUsuario() {
        //Puxar o principal, Se o usuario for root pega todos, se for User pega só os deles
        SecurityContext context = SecurityContextHolder.getContext();
        if (containsAuthority(context, Role.USER)) {
            Long codUsuario = ((UserDetailsImpl) context.getAuthentication().getPrincipal()).getUsuario().getCodigoUsuario();
            return new ResponseEntity<>(bemService.userBensList(codUsuario), HttpStatus.ACCEPTED);
        } else if (containsAuthority(context, Role.ROOT)) {
            return new ResponseEntity<>(bemService.findAll(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<List<Bem>>(List.of(), HttpStatus.UNAUTHORIZED);

    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar Bem")
    public void deleteBem(@PathVariable Long id) {
        bemService.delete(id);
    }


}
