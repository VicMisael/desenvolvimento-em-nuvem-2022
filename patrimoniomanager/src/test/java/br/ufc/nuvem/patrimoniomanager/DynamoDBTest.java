//package br.ufc.nuvem.patrimoniomanager;
//
//import br.ufc.nuvem.patrimoniomanager.model.DTO.ValidationDTO;
//import br.ufc.nuvem.patrimoniomanager.model.Validation;
//import br.ufc.nuvem.patrimoniomanager.strategy.DynamoDBValidationLogStrategy;
//import br.ufc.nuvem.patrimoniomanager.strategy.StorageStrategy;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class DynamoDBTest {
//
//    @Test
//    void testDynamoDBtest() {
//        DynamoDBValidationLogStrategy strategy = new DynamoDBValidationLogStrategy();
//        ValidationDTO dto = new ValidationDTO(3L, "é foda,", Boolean.FALSE);
//        strategy.uploadValidation(dto.toValidation());
//        strategy.getValidationListByBemId(3L);
//    };
//}
