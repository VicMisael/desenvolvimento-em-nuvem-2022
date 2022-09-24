package br.ufc.nuvem.patrimoniomanager.repository;

import br.ufc.nuvem.patrimoniomanager.model.Validation;

import java.util.List;

public interface ValidationRepository {
    public Validation addValidation(Validation validation);

    public List<Validation> getValidationByBemId(Long id);

}
