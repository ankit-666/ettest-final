package com.ettest.utility;

import com.ettest.entity.Token;
import com.ettest.entity.User;
import com.ettest.repository.TokenRepository;
import com.ettest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Component("com.ettest.utility.TokenUtility")
public class TokenUtility {

  @Autowired
  @Qualifier("com.ettest.repository.TokenRepository")
  private TokenRepository tokenRepository;

//  @Autowired
//  @Qualifier("com.ettest.repository.UserRepository")
//  private UserRepository userRepository;

  public String generateToken(String unhashedValue, Long userId)
      throws NoSuchProviderException, NoSuchAlgorithmException {
    byte[] salt = getSalt();
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(salt);
    byte[] bytes = md.digest(unhashedValue.getBytes());
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
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

//  public String validateToken(Long userId, String token) {
//    Token tokenFromDb = tokenRepository.findByUserId(userId);
//    if (tokenFromDb.getCreatedOn().isBefore(LocalDateTime.now().minusMinutes(15))) {
//      User user = userRepository.findById(userId).get();
//      String unhashedValue = user.getEmailId() + userId + LocalDateTime.now();
//      String newToken = null;
//      try {
//        newToken = generateToken(unhashedValue, userId);
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//      tokenFromDb.setToken(newToken);
//      token = newToken;
//      tokenRepository.save(tokenFromDb);
//      return token;
//    }
//    if(token != tokenFromDb.getToken()) {
//      try {
//        throw new InvalidKeyException();
//      } catch (InvalidKeyException e) {
//        e.printStackTrace();
//      }
//    }
//    return token;
//  }
}
