package br.ufc.nuvem.patrimoniomanager.service;

import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.DTO.BemEditDTO;
import br.ufc.nuvem.patrimoniomanager.model.util.FileData;
import br.ufc.nuvem.patrimoniomanager.repository.AppDataRepository;
import br.ufc.nuvem.patrimoniomanager.repository.BemRepository;
import br.ufc.nuvem.patrimoniomanager.repository.UsuarioRepository;
import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BemService {
    private final BemRepository bemRepository;
    private final AppDataRepository patrimonioDataRepository;

    private final UsuarioRepository usuarioRepository;

    private static final List<String> ALLOWED_EXTENSIONS = List.of("jpg", "jpeg", "png", "pdf");

    public Optional<Bem> findBemById(Long id) {
        return bemRepository.findById(id);
    }

    public List<Bem> findAll() {
        return bemRepository.findAll();
    }

    public boolean existsById(Long id) {
        return bemRepository.existsById(id);
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
            if (bem.get().getDirImagemBem() != null)
                patrimonioDataRepository.deleteData(bem.get().getDirImagemBem());

            bemRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
        }

    }

    public void deletebensByUserid(Long userid) {
        List<Bem> bensToDelete = bemRepository.findBemsByUsuario_CodigoUsuario(userid);
        bensToDelete.forEach(bem -> patrimonioDataRepository.deleteData(bem.getDirImagemBem()));
        bemRepository.deleteBemsByUsuario_CodigoUsuario(userid);

    }

    public Bem save(Bem bem) {
        if (usuarioRepository.existsById(bem.getUsuario().getCodigoUsuario()))
            return bemRepository.save(bem);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario não existe");
    }

    public Bem update(BemEditDTO bem) {
        Optional<Bem> bemOptional = bemRepository.findById(bem.getCodbem());
        if (bemOptional.isPresent()) {
            Bem foundBem = bemOptional.get();
            foundBem.setLocalizacao(bem.getLocalizacao());
            foundBem.setName(bem.getNome());
            return bemRepository.save(foundBem);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update without ID");
    }

    public Pair<FileData, Bem> generatePresignedUploadURL(Long id, String originalFilename) {
        final Optional<Bem> bemOptional = bemRepository.findById(id);
        final String fileExtension = Files.getFileExtension(Objects.requireNonNull(originalFilename));
        if (bemOptional.isPresent()) {
            if (ALLOWED_EXTENSIONS.contains(fileExtension)) {
                final Bem bem = bemOptional.get();

                final FileData fileData = patrimonioDataRepository.insertData(bem.getUsuario().getFolderName(), generateUploadFilename(bem,originalFilename));

                bem.setDirImagemBem(fileData.getFilename());
                bem.setBemUrl(patrimonioDataRepository.getBemUrl(bem.getDirImagemBem()));
                return Pair.of(fileData, bemRepository.save(bem));
            } else {
                log.info("invalid file");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Extensão invalida");
            }

        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update without ID");
    }

    private String generateUploadFilename(Bem bem, String originalFilename) {
        return bem.hashCode() + Files.getNameWithoutExtension(originalFilename) + UUID.randomUUID()
                + "." + Files.getFileExtension(originalFilename);
    }

}
