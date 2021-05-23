package com.ettest.entity;

import com.ettest.enums.UserType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DATA")
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Enumerated(EnumType.STRING)
  @Column(name = "USER_TYPE")
  private UserType userType;

  @Column(name = "EMAIL_ID")
  private String emailId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public User(String firstName, String lastName, UserType userType, String emailId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userType = userType;
    this.emailId = emailId;
  }

  public User() {
  }
}
