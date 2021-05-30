package com.ettest.model;

import javax.persistence.Entity;

@Entity
public class LoginModel {

  private String emialId;

  private String password;

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

  public LoginModel(String emialId, String password) {
    this.emialId = emialId;
    this.password = password;
  }
}
