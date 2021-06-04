package com.thesis.service.common.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class PersonBaseTable extends BaseTable {

  @NotNull
  @Column(unique = true)
  private int code;

  @NotBlank
  private String name;

  private String email;

  private String phone;

  private String gender;

}
