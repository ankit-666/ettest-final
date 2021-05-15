package com.ettest.utility;

import com.ettest.constants.Constants;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component("com.ettest.utility.AesCryptographerUtility")
public class AesCryptographerUtility {

  public String AesEncrypt(String stringToBeEncrypted, String secretKey)
      throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
      InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException,
      IllegalBlockSizeException {
    byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(),Constants.salt.getBytes(),65536,256);
    SecretKey tempSecretKey = factory.generateSecret(keySpec);
    SecretKey secretKeyActive = new SecretKeySpec(tempSecretKey.getEncoded(),"AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE,secretKeyActive,ivParameterSpec);
    return Base64.getEncoder().encodeToString(cipher.doFinal(stringToBeEncrypted.getBytes(StandardCharsets.UTF_8)));
  }

  public String AesDecrypt(String encryptedPath, String secretKey)
      throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
      InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException,
      IllegalBlockSizeException {
    byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(),Constants.salt.getBytes(),65536,256);
    SecretKey tempSecretKey = factory.generateSecret(keySpec);
    SecretKey secretKeyActive = new SecretKeySpec(tempSecretKey.getEncoded(),"AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE,secretKeyActive,ivParameterSpec);
    return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedPath)));
  }
}
