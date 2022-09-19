package br.ufc.nuvem.patrimoniomanager.controller;

import br.ufc.nuvem.patrimoniomanager.model.DTO.UsuarioDTO;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import br.ufc.nuvem.patrimoniomanager.service.UsuarioService;
import br.ufc.nuvem.patrimoniomanager.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<Usuario> insertPessoa(@RequestParam UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.save(usuarioDTO.toUsuario()), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllPessoas() {
        return new ResponseEntity<>(usuarioService.find(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getPessoa(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.find(id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePessoa(@RequestParam Long id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<Usuario> updatePessoa(Usuario usuario) {
        return new ResponseEntity<>(usuarioService.update(usuario), HttpStatus.ACCEPTED);
    }
}
