package com.ettest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TOKEN")
public class Token {

  @Id
  private Long id;

  @Column(name = "TOKEN")
  private String token;

  @Column(name = "CREATED_ON")
  private LocalDateTime createdOn;

  public Token(Long id, String token, LocalDateTime createdOn) {
    this.id = id;
    this.token = token;
    this.createdOn = createdOn;
  }

  public Token() {
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }
}
