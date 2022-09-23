package br.ufc.nuvem.patrimoniomanager.controller;

import br.ufc.nuvem.patrimoniomanager.model.DTO.UsuarioDTO;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import br.ufc.nuvem.patrimoniomanager.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<Usuario> insertPessoa(@RequestBody UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.save(usuarioDTO.toUsuario()), HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> getAllPessoas() {
        return new ResponseEntity<>(usuarioService.find(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getPessoa(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.find(id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletePessoa(@RequestBody Long id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping()
    public ResponseEntity<Usuario> updatePessoa(Usuario usuario) {
        return new ResponseEntity<>(usuarioService.update(usuario), HttpStatus.ACCEPTED);
    }
}
