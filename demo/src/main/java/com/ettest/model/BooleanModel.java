package com.ettest.model;

public class BooleanModel {

  String token;

  Boolean bool;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Boolean getBool() {
    return bool;
  }

  public void setBool(Boolean bool) {
    this.bool = bool;
  }

  public BooleanModel(String token, Boolean bool) {
    this.token = token;
    this.bool = bool;
  }

  public BooleanModel() {
  }
}
