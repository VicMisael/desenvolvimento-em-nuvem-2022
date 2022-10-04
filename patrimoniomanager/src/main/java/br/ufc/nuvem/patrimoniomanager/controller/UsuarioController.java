package br.ufc.nuvem.patrimoniomanager.controller;

import br.ufc.nuvem.patrimoniomanager.configuration.security.UserDetailsImpl;
import br.ufc.nuvem.patrimoniomanager.model.DTO.UsuarioDTO;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import br.ufc.nuvem.patrimoniomanager.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@CrossOrigin("*")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping()
    public ResponseEntity<Usuario> insertPessoa(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        return new ResponseEntity<>(usuarioService.save(usuarioDTO.toUsuario()), HttpStatus.ACCEPTED);
    }

    @PostMapping("/root")
    public ResponseEntity<Usuario> insertPessoaRoot(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        return new ResponseEntity<>(usuarioService.save(usuarioDTO.toUsuarioRoot()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/me")
    public ResponseEntity<Optional<Usuario>> getMe() {
        SecurityContext context = SecurityContextHolder.getContext();
        Long codUsuario = ((UserDetailsImpl) context.getAuthentication().getPrincipal()).getUsuario().getCodigoUsuario();
        return new ResponseEntity<>(usuarioService.find(codUsuario), HttpStatus.ACCEPTED);

    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> getAllPessoas() {
        return new ResponseEntity<>(usuarioService.find(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getPessoa(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.find(id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping()
    public ResponseEntity<Usuario> updatePessoa(Usuario usuario) {
        return new ResponseEntity<>(usuarioService.update(usuario), HttpStatus.ACCEPTED);
    }
}
