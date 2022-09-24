package br.ufc.nuvem.patrimoniomanager;

import br.ufc.nuvem.patrimoniomanager.strategy.S3StorageStrategy;
import br.ufc.nuvem.patrimoniomanager.strategy.StorageStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class S3StorageStrategyTest {

    @Test
    void testBucketCreation(){
        StorageStrategy s3StorageStrategy;
    }
}
