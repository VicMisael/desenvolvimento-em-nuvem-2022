package br.ufc.nuvem.patrimoniomanager.service;

import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import br.ufc.nuvem.patrimoniomanager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioService {

    private final S3Service s3Service;
    private final UsuarioRepository pessoaRepository;

    public Usuario save(Usuario usuario) {
        return pessoaRepository.save(usuario);
    }

    public Optional<Usuario> find(Long id) {
       return pessoaRepository.findById(id);
    }

    public List<Usuario> find() {
        List<Usuario> usuarios = new ArrayList<>();
        pessoaRepository.findAll().forEach(usuarios::add);
        return usuarios;
    }


    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Usuario update(Usuario usuario) {
        if (pessoaRepository.existsById(usuario.getCodigoUsuario())) {
            return pessoaRepository.save(usuario);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update without ID");

    }

    public void insertPhoto(Usuario usuario) {

    }

}
