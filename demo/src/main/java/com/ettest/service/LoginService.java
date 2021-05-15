package com.ettest.service;

import com.ettest.model.DecryptModel;
import com.ettest.model.LoginModel;
import com.ettest.model.RegisterModel;
import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

public interface LoginService {

  ResponseEntity<Long> register(RegisterModel registerModel);

  HttpStatus login(LoginModel loginModel, Long userId)
      throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
      IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException,
      InvalidKeySpecException, IOException, WriterException, MessagingException;

  ResponseEntity<String> decrypt(DecryptModel decryptModel, Long userId)
      throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
      IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException,
      InvalidKeySpecException;

  HttpStatus generateToken(String emailId, Long userId)
      throws NoSuchProviderException, NoSuchAlgorithmException;
}
