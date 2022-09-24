package br.ufc.nuvem.patrimoniomanager.service;

import br.ufc.nuvem.patrimoniomanager.model.Validation;
import br.ufc.nuvem.patrimoniomanager.repository.ValidationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final ValidationRepository validationRepository;

    public Validation createValidation(Validation validation){
      return validationRepository.addValidation(validation);
    };

    public List<Validation> getValidationListByBemId(Long bemId){
        return validationRepository.getValidationByBemId(bemId);
    }

}
