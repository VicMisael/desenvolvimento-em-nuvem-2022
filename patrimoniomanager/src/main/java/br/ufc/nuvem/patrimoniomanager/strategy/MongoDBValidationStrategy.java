package br.ufc.nuvem.patrimoniomanager.strategy;

import br.ufc.nuvem.patrimoniomanager.model.Validation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoDBValidationStrategy implements ValidationLogStrategy {
    @Override
    public Validation uploadValidation(Validation validation) {
        return validation;
    }

    @Override
    public List<Validation> getValidationListByBemId(Long bemid) {
        return List.of();
    }
}
