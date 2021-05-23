package com.ettest.model;

import com.ettest.enums.UserType;

public class RegisterModel {

  private String firstName;

  private String lastName;

  private UserType userType;

  private String email;

  private String password;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RegisterModel(String firstName, String lastName, UserType userType, String email,
      String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userType = userType;
    this.email = email;
    this.password = password;
  }

  public RegisterModel() {
  }
}
