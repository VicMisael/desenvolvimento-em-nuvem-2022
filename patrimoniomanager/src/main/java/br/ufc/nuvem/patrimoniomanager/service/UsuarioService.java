package br.ufc.nuvem.patrimoniomanager.service;

import br.ufc.nuvem.patrimoniomanager.model.DTO.UsuarioEditDTO;
import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import br.ufc.nuvem.patrimoniomanager.repository.PatrimonioDataRepository;
import br.ufc.nuvem.patrimoniomanager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PatrimonioDataRepository patrimonioDataRepository;

    public Usuario save(Usuario usuario) {
        if (!usuarioRepository.existsUsuarioByIdentificacao(usuario.getIdentificacao()))
            return usuarioRepository.save(usuario);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user already exists ");
    }

    public Optional<Usuario> find(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> find() {
        return new ArrayList<>(usuarioRepository.findAll());
    }


    public void delete(Long id) {
        if (find(id).isPresent())
            find(id).get().getBens().forEach(bem -> patrimonioDataRepository.deleteData(bem.getDirImagemBem()));
        usuarioRepository.deleteById(id);
    }

    public Usuario update(UsuarioEditDTO usuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuario.getId());
        if (optionalUsuario.isPresent()) {
            Usuario usuario1 = optionalUsuario.get();
            usuario1.setNome(usuario.getName());
            usuario1.setSenha(usuario.getSenha());
            usuario1.setIdentificacao(usuario.getIdentificacao());
            usuario1.setEmail(usuario.getEmail());
            return usuarioRepository.save(usuario1);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update without ID");
    }

}
