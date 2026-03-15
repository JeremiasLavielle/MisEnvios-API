package com.misenvios.misenviosapi.util;

import com.misenvios.misenviosapi.config.AndreaniConfig;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CryptoUtil {

    public static String encryptTracking(String numero) {
        try {
            String json = String.format(
                    "{\"idReceptor\":1,\"idSistema\":1,\"userData\":\"{\\\"mail\\\":\\\"\\\"}\",\"numeroAndreani\":\"%s\"}",
                    numero
            );

            SecretKeySpec keySpec = new SecretKeySpec(
                    AndreaniConfig.API_KEY.getBytes(StandardCharsets.UTF_8), "AES"
            );
            IvParameterSpec ivSpec = new IvParameterSpec(
                    AndreaniConfig.API_IV.getBytes(StandardCharsets.UTF_8)
            );

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            byte[] encrypted = cipher.doFinal(json.getBytes(StandardCharsets.UTF_8));
            String base64 = Base64.getEncoder().encodeToString(encrypted);

            return URLEncoder.encode(base64, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("Error encriptando número de tracking: " + numero, e);
        }
    }

    public static String decryptTracking(String encryptedUrlEncoded) {
        try {
            String base64 = java.net.URLDecoder.decode(encryptedUrlEncoded, StandardCharsets.UTF_8);
            byte[] encrypted = Base64.getDecoder().decode(base64);

            SecretKeySpec keySpec = new SecretKeySpec(
                    AndreaniConfig.API_KEY.getBytes(StandardCharsets.UTF_8), "AES"
            );
            IvParameterSpec ivSpec = new IvParameterSpec(
                    AndreaniConfig.API_IV.getBytes(StandardCharsets.UTF_8)
            );

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted, StandardCharsets.UTF_8).trim();

        } catch (Exception e) {
            throw new RuntimeException("Error desencriptando payload", e);
        }
    }
}