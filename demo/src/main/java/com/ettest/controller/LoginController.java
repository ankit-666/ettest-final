package com.ettest.controller;

import com.ettest.model.DecryptModel;
import com.ettest.model.LoginModel;
import com.ettest.model.RegisterModel;
import com.ettest.service.LoginService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping(value = "/ettest")
public class LoginController {

  @Autowired
  @Qualifier("com.ettest.service.impl.LoginServiceImpl")
  private LoginService loginService;

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ResponseEntity<Long> register(@RequestBody RegisterModel registerModel) {
    ResponseEntity<Long> httpResponse = loginService.register(registerModel);
    return httpResponse;
  }

  @RequestMapping(value = "/login", method = RequestMethod.PUT)
  public ResponseEntity<HttpStatus> login(@RequestBody LoginModel loginModel,
      @RequestHeader("userId") Long userId)
      throws IOException, NoSuchAlgorithmException, WriterException, InvalidKeyException,
      InvalidAlgorithmParameterException, NoSuchPaddingException, BadPaddingException,
      InvalidKeySpecException, IllegalBlockSizeException, MessagingException {
    HttpStatus httpStatus = loginService.login(loginModel, userId);
    return new ResponseEntity<>(httpStatus);
  }

  @RequestMapping(value = "/decrypt", method = RequestMethod.PUT)
  public ResponseEntity<String> decrypt(@RequestBody DecryptModel decryptModel,
      @RequestHeader("userId") Long userId)
      throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
      IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
    ResponseEntity<String> path = loginService.decrypt(decryptModel, userId);
    return path;
  }

  @RequestMapping(value = "/generateToken", method = RequestMethod.PUT)
  public ResponseEntity<HttpStatus> generateToken(@RequestParam("emailId") String emailId,
      @RequestHeader("userId") Long userId)
      throws NoSuchProviderException, NoSuchAlgorithmException {
    return new ResponseEntity<>(loginService.generateToken(emailId, userId));
  }

}
