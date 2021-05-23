//package com.ettest.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "DOCUMENT")
//public class Document {
//
//  @Id
//  @GeneratedValue
//  private Long id;
//
//  @Column(name = "UPLOADED_BY")
//  private Long uploadedBy;
//
//  @Column(name = "UPLOADED_ON")
//  private LocalDateTime uploadedOn;
//
//  @Column(name = "TO_BE_SIGNED_BY")
//  private Long toBeSignedBy;
//
//  @Column(name = "IS_SIGNED")
//  private Boolean isSigned;
//
//  @Column(name = "SIGNED_ON")
//  private LocalDateTime signedOn;
//
//  @Column(name = "UPLOADED_DOCUMENT_LINK")
//  private String uploadedDocumentLink;
//
//  @Column(name = "SIGNED_DOCUMENT_LINK")
//  private String signedDocumentLink;
//
//  @Column(name = "HAS_ISSUE")
//  private Boolean hasIssue;
//
//  public Long getId() {
//    return id;
//  }
//
//  public void setId(Long id) {
//    this.id = id;
//  }
//
//  public Long getUploadedBy() {
//    return uploadedBy;
//  }
//
//  public void setUploadedBy(Long uploadedBy) {
//    this.uploadedBy = uploadedBy;
//  }
//
//  public LocalDateTime getUploadedOn() {
//    return uploadedOn;
//  }
//
//  public void setUploadedOn(LocalDateTime uploadedOn) {
//    this.uploadedOn = uploadedOn;
//  }
//
//  public Long getToBeSignedBy() {
//    return toBeSignedBy;
//  }
//
//  public void setToBeSignedBy(Long toBeSignedBy) {
//    this.toBeSignedBy = toBeSignedBy;
//  }
//
//  public Boolean getSigned() {
//    return isSigned;
//  }
//
//  public void setSigned(Boolean signed) {
//    isSigned = signed;
//  }
//
//  public LocalDateTime getSignedOn() {
//    return signedOn;
//  }
//
//  public void setSignedOn(LocalDateTime signedOn) {
//    this.signedOn = signedOn;
//  }
//
//  public String getUploadedDocumentLink() {
//    return uploadedDocumentLink;
//  }
//
//  public void setUploadedDocumentLink(String uploadedDocumentLink) {
//    this.uploadedDocumentLink = uploadedDocumentLink;
//  }
//
//  public String getSignedDocumentLink() {
//    return signedDocumentLink;
//  }
//
//  public void setSignedDocumentLink(String signedDocumentLink) {
//    this.signedDocumentLink = signedDocumentLink;
//  }
//
//  public Boolean getHasIssue() {
//    return hasIssue;
//  }
//
//  public void setHasIssue(Boolean hasIssue) {
//    this.hasIssue = hasIssue;
//  }
//
//  public Document(Long id, Long uploadedBy, LocalDateTime uploadedOn, Long toBeSignedBy,
//      Boolean isSigned, LocalDateTime signedOn, String uploadedDocumentLink,
//      String signedDocumentLink, Boolean hasIssue) {
//    this.id = id;
//    this.uploadedBy = uploadedBy;
//    this.uploadedOn = uploadedOn;
//    this.toBeSignedBy = toBeSignedBy;
//    this.isSigned = isSigned;
//    this.signedOn = signedOn;
//    this.uploadedDocumentLink = uploadedDocumentLink;
//    this.signedDocumentLink = signedDocumentLink;
//    this.hasIssue = hasIssue;
//  }
//
//  public Document() { }
//}
