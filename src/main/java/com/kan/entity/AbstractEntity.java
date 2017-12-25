//package com.kan.entity;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.PreUpdate;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.persistence.Version;
//
//@MappedSuperclass
//public abstract class AbstractEntity {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
//  @Column(name = "ID", updatable = false, nullable = false)
//  private Long id;
//
//  @Temporal(TemporalType.TIMESTAMP)
//  @Column(name = "LAST_UPDATE_TIMESTAMP", nullable = false)
//  private Date lastUpdateTimestamp;
//
//  @Version
//  @Column(name = "VERSION", nullable = false)
//  private int version = 0;
//
//  @Override
//  public String toString() {
//    return String.valueOf(id);
//  }
//
//  @PreUpdate
//  protected void onPreUpdate() {
//    lastUpdateTimestamp = new Date();
//  }
//
//  public Long getId() {
//    return id;
//  }
//
//  public Date getLastUpdateTimestamp() {
//    return lastUpdateTimestamp;
//  }
//
//  public int getVersion() {
//    return version;
//  }
//}