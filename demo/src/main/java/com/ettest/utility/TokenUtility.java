package com.ettest.utility;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

@Component("com.ettest.utility.TokenUtility")
public class TokenUtility {

  public String generateToken(String unhashedValue, Long userId)
      throws NoSuchProviderException, NoSuchAlgorithmException {
    byte[] salt = getSalt();
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(salt);
    byte[] bytes = md.digest(unhashedValue.getBytes());
    StringBuilder sb = new StringBuilder();
    for(int i=0; i< bytes.length ;i++)
    {
      sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    }
    String token = sb.toString();
    return token;
  }

  private byte[] getSalt() throws NoSuchProviderException, NoSuchAlgorithmException {
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
    byte[] salt = new byte[16];
    sr.nextBytes(salt);
    return salt;
  }
}
