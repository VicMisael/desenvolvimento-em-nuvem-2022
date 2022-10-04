package br.ufc.nuvem.patrimoniomanager.service;

import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.repository.BemRepository;
import br.ufc.nuvem.patrimoniomanager.repository.PatrimonioDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BemService {
    private final BemRepository bemRepository;
    private final PatrimonioDataRepository patrimonioDataRepository;

    public Optional<Bem> findBemById(Long id) {
        return bemRepository.findById(id);
    }

    public List<Bem> findAll() {
        return bemRepository.findAll();
    }

    public List<Bem> searchBensByName(Optional<Long> userId, String name) {
        if (userId.isPresent())
            return bemRepository.findBemsByUsuario_CodigoUsuarioAndNameContainingIgnoreCase(userId.get(), name);
        else
            return bemRepository.findBemsByNameContainingIgnoreCase(name);
    }

    public List<Bem> searchBensWithLocalization(Optional<Long> userId, String localizacao) {
        if (userId.isPresent())
            return bemRepository.findBemsByUsuario_CodigoUsuarioAndLocalizacaoIgnoreCase(userId.get(), localizacao);
        else
            return bemRepository.findBemsByLocalizacaoContainingIgnoreCase(localizacao);

    }

    public List<Bem> searchBensByLocalizationAndName(Optional<Long> userId, String name, String localizacao) {
        if (userId.isPresent())
            return bemRepository.findBemsByUsuario_CodigoUsuarioAndNameContainingIgnoreCaseAndLocalizacaoContainingIgnoreCase(userId.get(), name, localizacao);
        else
            return bemRepository.findBemsByLocalizacaoContainingIgnoreCaseAndNameContainingIgnoreCase(localizacao, name);
    }

    public List<Bem> userBensList(Long id) {
        return bemRepository.findBemsByUsuario_CodigoUsuario(id);
    }

    public void delete(Long id) {
        Optional<Bem> bem = findBemById(id);
        if (bem.isPresent()) {
            if(bem.get().getDirImagemBem() != null )
                patrimonioDataRepository.deleteData(bem.get().getDirImagemBem());

            bemRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
        }

    }

    public Bem save(Bem bem) {
        Bem savedBem = bemRepository.save(bem);
        return bemRepository.save(savedBem);
    }

    public Bem update(Long id, Bem bem) {
        Optional<Bem> bemOptional = bemRepository.findById(id);
        if (bemOptional.isPresent()) {
            Bem foundBem = bemOptional.get();
            if (Objects.equals(foundBem.getUsuario().getCodigoUsuario(), bem.getUsuario().getCodigoUsuario())) {
                return bemRepository.save(bem);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Codigo usuario diferente");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update without ID");
    }

    public Bem addFile(Long id, MultipartFile file) {
        Optional<Bem> bemOptional = bemRepository.findById(id);
        if (bemOptional.isPresent()) {
            Bem bem = bemOptional.get();

            bem.setDirImagemBem(patrimonioDataRepository.insertData(bem.getUsuario().getFolderName(), file));
            bem.setBemUrl(patrimonioDataRepository.getBemUrl(bem.getDirImagemBem()));
            return bemRepository.save(bem);

        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update without ID");

    }

}
