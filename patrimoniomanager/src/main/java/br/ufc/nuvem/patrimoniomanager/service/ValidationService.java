package br.ufc.nuvem.patrimoniomanager.service;

import br.ufc.nuvem.patrimoniomanager.model.Bem;
import br.ufc.nuvem.patrimoniomanager.model.Validation;
import br.ufc.nuvem.patrimoniomanager.repository.ValidationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final ValidationRepository validationRepository;
    private final BemService bemService;

    public Validation createValidation(Validation validation) {
        Optional<Bem> bemOptional = bemService.findBemById(Long.valueOf(validation.getIdBem()));
        if (bemOptional.isPresent()) {
            Bem bem = bemOptional.get();
            validation.setUserid(String.valueOf(bem.getUsuario().getCodigoUsuario()));
            return validationRepository.addValidation(validation);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid bem");
    }


    public List<Validation> getValidationListByBemId(Long bemId) {
        return validationRepository.getValidationByBemId(bemId);
    }

}
