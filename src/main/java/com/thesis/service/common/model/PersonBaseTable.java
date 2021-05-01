package com.thesis.service.common.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class PersonBaseTable extends BaseTable {

  @NotBlank
  private String name;

  @Email
  private String email;

  private String phone;

  private String gender;

}
