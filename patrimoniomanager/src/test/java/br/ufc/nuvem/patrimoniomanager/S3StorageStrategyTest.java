//package br.ufc.nuvem.patrimoniomanager;
//
//import br.ufc.nuvem.patrimoniomanager.strategy.S3StorageStrategy;
//import br.ufc.nuvem.patrimoniomanager.strategy.StorageStrategy;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.io.*;
//
//public class S3StorageStrategyTest {
//
//    @Test
//    void testBucketCreation() {
//        System.out.println(new BCryptPasswordEncoder().encode("senha"));
//    }
//
//    @Test
//    void insertFile() {
//        StorageStrategy s3StorageStrategy = new S3StorageStrategy("patrimoniomanager-files2");
//
//        //s3StorageStrategy.insertFileAtFolder("emailsacolaitessa1728327602",createSampleFile());
//    }
//
//    @Test
//    void getFileReference() {
//        StorageStrategy s3StorageStrategy = new S3StorageStrategy("patrimoniomanager-files2");
//
//        s3StorageStrategy.getUrl("emailsacolaitessa1728327602/aws-java-sdk-6376925060632508389.txt");
//    }
//
//    @Test
//    void deleteFile() {
//        StorageStrategy s3StorageStrategy = new S3StorageStrategy("patrimoniomanager-files2");
//
//        s3StorageStrategy.deleteFile("emailsacolaitessa1728327602/aws-java-sdk-6376925060632508389.txt");
//    }
//
//
//    private static File createSampleFile() {
//
//        try {
//            File file = File.createTempFile("aws-java-sdk-", ".txt");
//            file.deleteOnExit();
//
//            Writer writer = new OutputStreamWriter(new FileOutputStream(file));
//            writer.write("abcdefghijklmnopqrstuvwxyz\n");
//            writer.write("01234567890112345678901234\n");
//            writer.write("!@#$%^&*()-=[]{};':',.<>/?\n");
//            writer.write("01234567890112345678901234\n");
//            writer.write("abcdefghijklmnopqrstuvwxyz\n");
//            writer.close();
//
//            return file;
//        } catch (Exception ex) {
//            return null;
//        }
//
//
//    }
//}
