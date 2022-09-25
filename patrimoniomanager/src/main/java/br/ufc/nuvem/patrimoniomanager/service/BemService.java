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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BemService {
    private final BemRepository bemRepository;
    private final PatrimonioDataRepository patrimonioDataRepository;

    public List<Bem> searchBensByName(String name) {
        return bemRepository.findBemsByNameContainingIgnoreCase(name);
    }

    public Optional<Bem> findBemById(Long id) {
        return bemRepository.findById(id);
    }

    public List<Bem> findAll() {
        return bemRepository.findAll();
    }

    public List<Bem> searchBens(Optional<Long> userId, String name) {
        if (userId.isPresent())
            return bemRepository.findBemsByUsuario_CodigoUsuarioAndNameContainingIgnoreCase(userId.get(), name);
        else
            return bemRepository.findBemsByNameContainingIgnoreCase(name);
    }

    public List<Bem> searchBensAndLocalization(Optional<Long> userId, String localizacao) {
        if (userId.isPresent())
            return bemRepository.findBemsByUsuario_CodigoUsuarioAndLocalizacaoIgnoreCase(userId.get(), localizacao);
        else
            return bemRepository.findBemsByLocalizacaoContainingIgnoreCase(localizacao);

    }

    public List<Bem> searchBensAndNameAndLocalization(Optional<Long> userId, String name, String localizacao) {
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
            patrimonioDataRepository.deleteData(bem.get().getDirImagemBem());
            bemRepository.deleteById(id);
        }

    }

    public Bem save(Bem bem) {
        return bemRepository.save(bem);
    }

    public Bem update(Long id, Bem bem) {
        if (bemRepository.existsById(id)) {
            return bemRepository.save(bem);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update without ID");
    }

    public Bem saveFile(Long id, MultipartFile file) {
        Optional<Bem> bem = bemRepository.findById(id);
        if (bem.isPresent()) {
            Bem bem2 = bem.get();
            bem2.setDirImagemBem(patrimonioDataRepository.insertData(bem2.getUsuario().getFolderName(), file));
            bem2.setBemUrl(patrimonioDataRepository.getBemUrl(bem2.getDirImagemBem()));
            return bemRepository.save(bem2);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update without ID");
    }


}
