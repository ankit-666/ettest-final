package com.ettest.service.impl;

import com.ettest.constants.Constants;
import com.ettest.entity.LoginCreds;
import com.ettest.entity.Token;
import com.ettest.entity.User;
import com.ettest.model.DecryptModel;
import com.ettest.model.LoginModel;
import com.ettest.model.RegisterModel;
import com.ettest.repository.LoginCredsRepository;
import com.ettest.repository.TokenRepository;
import com.ettest.repository.UserRepository;
import com.ettest.service.LoginService;
import com.ettest.utility.AesCryptographerUtility;
import com.ettest.utility.MailUtility;
import com.ettest.utility.QrUtility;
import com.ettest.utility.TokenUtility;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service("com.ettest.service.impl.LoginServiceImpl")
public class LoginServiceImpl implements LoginService {

  @Autowired
  @Qualifier("com.ettest.repository.LoginCredsRepository")
  LoginCredsRepository loginCredsRepository;

  @Autowired
  @Qualifier("com.ettest.repository.UserRepository")
  UserRepository userRepository;

  @Autowired
  @Qualifier("com.ettest.utility.AesCryptographerUtility")
  AesCryptographerUtility aesCryptographerUtility;

  @Autowired
  @Qualifier("com.ettest.utility.QrUtility")
  QrUtility qrUtility;

  @Autowired
  @Qualifier("com.ettest.utility.MailUtility")
  MailUtility mailUtility;

  @Autowired
  @Qualifier("com.ettest.utility.TokenUtility")
  TokenUtility tokenUtility;

  @Autowired
  @Qualifier("com.ettest.repository.TokenRepository")
  TokenRepository tokenRepository;

  @Override
  public ResponseEntity<Long> register(RegisterModel registerModel) {
    User user = new User(registerModel.getFirstName(), registerModel.getLastName(),
        registerModel.getUserType(), registerModel.getEmail());
    Long id = userRepository.save(user).getId();
    LoginCreds loginCreds =
        new LoginCreds(id, registerModel.getEmail(), registerModel.getPassword());
    Long newId = loginCredsRepository.save(loginCreds).getId();
    if (newId == null) {
      return new ResponseEntity<>(newId, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(newId, HttpStatus.ACCEPTED);
  }

  @Override
  public HttpStatus login(LoginModel loginModel, Long userId)
      throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
      IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException,
      InvalidKeySpecException, IOException, WriterException, MessagingException {
    LoginCreds loginCreds = loginCredsRepository.findById(userId).get();
    if (Boolean.FALSE.equals(loginCreds.validate(loginModel))) {
      return HttpStatus.BAD_REQUEST;
    }
    String linkToBeSent = Constants.baseLink + loginCreds.getEmailId();
    String encryptedLink =
        aesCryptographerUtility.AesEncrypt(linkToBeSent, loginModel.getPassword());
    String path = loginCreds.getEmailId().split(".") + ".png";
    Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
    hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
    qrUtility.createQr(encryptedLink, path, "UTF-8", hashMap, 200, 200);
    mailUtility.sendQrViaEmail(loginCreds.getEmailId(), path);
    return HttpStatus.ACCEPTED;
  }

  @Override
  public ResponseEntity<String> decrypt(DecryptModel decryptModel, Long userId)
      throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
      IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException,
      InvalidKeySpecException {
    LoginCreds loginCreds = loginCredsRepository.findById(userId).get();
    if (Boolean.FALSE.equals(loginCreds.validate(decryptModel))) {
      return new ResponseEntity<>((String) null, HttpStatus.BAD_REQUEST);
    }
    String path = aesCryptographerUtility
        .AesDecrypt(decryptModel.getEncryptedPath(), decryptModel.getPassword());
    return new ResponseEntity<>(path, HttpStatus.ACCEPTED);
  }

  @Override
  public HttpStatus generateToken(String emailId, Long userId)
      throws NoSuchProviderException, NoSuchAlgorithmException {
    LocalDateTime time = LocalDateTime.now();
    String unhashedValue = emailId + userId + time;
    String token = tokenUtility.generateToken(unhashedValue, userId);
    Token userToken = new Token(userId, token, time);
    tokenRepository.save(userToken);
    return HttpStatus.ACCEPTED;
  }
}
