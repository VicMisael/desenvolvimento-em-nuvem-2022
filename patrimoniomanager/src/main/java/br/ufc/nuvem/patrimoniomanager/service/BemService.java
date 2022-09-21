package br.ufc.nuvem.patrimoniomanager.service;

import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.repository.BemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BemService {
    private final BemRepository bemRepository;

    public Optional<Bem> getBemById(Long id){
        return bemRepository.findById(id);
    }
    public void delete(Long id){
        bemRepository.deleteById(id);
    }

    public Bem save(Bem bem){
        return bemRepository.save(bem);
    }
    public Bem update(Bem bem) {
        if (bem.getCodArquivo()!=null && bemRepository.existsById(bem.getCodArquivo())) {
            return bemRepository.save(bem);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update without ID");
    }

}
