package com.ettest.model;

public class DecryptModel {

  private String emialId;

  private String password;

  private String encryptedPath;

  public String getEmialId() {
    return emialId;
  }

  public void setEmialId(String emialId) {
    this.emialId = emialId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEncryptedPath() {
    return encryptedPath;
  }

  public void setEncryptedPath(String decryptedPath) {
    this.encryptedPath = decryptedPath;
  }

  public DecryptModel(String emialId, String password, String decryptedPath) {
    this.emialId = emialId;
    this.password = password;
    this.encryptedPath= decryptedPath;
  }
}
