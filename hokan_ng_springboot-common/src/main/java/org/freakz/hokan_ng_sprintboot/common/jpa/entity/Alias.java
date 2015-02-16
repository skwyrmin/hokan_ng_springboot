package org.freakz.hokan_ng_sprintboot.common.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Date: 23.1.2012
 * Time: 11:34
 *
 * @author Petri Airio
 */
@Entity
@Table(name = "ALIAS")
public class Alias implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private long aliasId;

  @Column(name = "ALIAS")
  private String alias;

  @Column(name = "ALIAS_TYPE")
  @Enumerated(EnumType.STRING)
  private AliasType aliasType;

  @Column(name = "COMMAND")
  private String command;

  @Column(name = "CREATED_BY")
  private String createdBy;

  @Column(name = "CREATED")
  private Date created;


  public Alias() {
  }

  public long getAliasId() {
    return aliasId;
  }

  public void setAliasId(long aliasId) {
    this.aliasId = aliasId;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public AliasType getAliasType() {
    return aliasType;
  }

  public void setAliasType(AliasType aliasType) {
    this.aliasType = aliasType;
  }
}