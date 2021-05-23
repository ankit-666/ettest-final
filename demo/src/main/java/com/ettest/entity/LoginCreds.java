package com.ettest.entity;

import com.ettest.model.DecryptModel;
import com.ettest.model.LoginModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOGIN_CREDENTIALSS")
public class LoginCreds {

  @Id
  private Long id;

  @Column(name = "EMAIL_ID")
  private String emailId;

  @Column(name = "PASSWORD")
  private String password;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LoginCreds(Long id, String emailId, String password) {
    this.id = id;
    this.emailId = emailId;
    this.password = password;
  }

  public LoginCreds() {
  }

  public Boolean validate(LoginModel loginModel) {
    if (this.getEmailId() != loginModel.getEmialId() || this.getPassword() != loginModel.getPassword()) {
       return false;
    }
    return true;
  }

  public Boolean validate(DecryptModel loginModel) {
    if (this.getEmailId() != loginModel.getEmialId() || this.getPassword() != loginModel.getPassword()) {
      return false;
    }
    return true;
  }
}
