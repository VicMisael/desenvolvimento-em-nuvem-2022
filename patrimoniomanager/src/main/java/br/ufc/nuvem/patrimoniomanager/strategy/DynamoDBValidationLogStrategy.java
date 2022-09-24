package br.ufc.nuvem.patrimoniomanager.strategy;

import br.ufc.nuvem.patrimoniomanager.model.Validation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DynamoDBValidationLogStrategy implements ValidationLogStrategy {

    public DynamoDBValidationLogStrategy() {

    }

    @Override
    public Validation uploadValidation(Validation validation) {
        return null;
    }

    @Override
    public List<Validation> getValidationListByBemId(Long id) {
        return null;
    }
}
