package br.ufc.nuvem.patrimoniomanager.service;

import br.ufc.nuvem.patrimoniomanager.model.Validation;
import br.ufc.nuvem.patrimoniomanager.repository.ValidationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final ValidationRepository validationRepository;
    private final BemService bemService;

    public Validation createValidation(Validation validation) {
        if (bemService.existsById(Long.valueOf(validation.getIdBem()))) {
            return validationRepository.addValidation(validation);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid bem");
    }


    public List<Validation> getValidationListByBemId(Long bemId) {
        return validationRepository.getValidationByBemId(bemId);
    }

}
