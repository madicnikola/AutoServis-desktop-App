/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Nikola
 */
public class PasswordHash {

    private static PasswordHash instance;
    private final String ALGORITHM = "MD5";

    private PasswordHash() {
    }

    public static PasswordHash getInstance() {
        if (instance == null) {
            instance = new PasswordHash();
        }
        return instance;
    }

    public String hash(String plainText) {
        String password = "";
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(plainText.getBytes());

            byte[] bytes = md.digest();
            password = DatatypeConverter.printHexBinary(bytes).toLowerCase();

        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Greska prilikom generisanja lozinke");
        }
        return password;
    }

}
