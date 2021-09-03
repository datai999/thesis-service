package com.thesis.service.common.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class PersonBaseTable extends BaseTable {

  @Column(unique = true)
  private String code;

  private String firstName;

  private String lastName;

  private String email;

  private String phone;

  @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
  private Boolean male;

  public String getFullName() {
    return String.format("%s %s", this.firstName, this.lastName);
  }

}
