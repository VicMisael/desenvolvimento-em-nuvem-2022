package br.ufc.nuvem.patrimoniomanager.strategy;

import br.ufc.nuvem.patrimoniomanager.model.Validation;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ValidationLogStrategy {
    public Validation uploadValidation(Validation validation);

    public List<Validation> getValidationListByBemId(Long id);
}
