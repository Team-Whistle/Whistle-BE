package com;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JasyptTest {

    public static void main(String[] args) {

        String password = "didwjs12";

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("chzhznzl");
        jasypt.setAlgorithm("PBEWITHMD5ANDDES");

        String encryptedText = jasypt.encrypt(password);
        String decryptedText = jasypt.decrypt(encryptedText);

        System.out.println("encryptedText = " + encryptedText);
        System.out.println("decryptedText = " + decryptedText);
    }

}