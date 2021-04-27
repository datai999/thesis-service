package com.thesis.service.model.person;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.thesis.service.model.br.BrBaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class PsBaseTable extends BrBaseTable {

  @NotBlank
  private String name;

  @Email
  private String email;

  private String phone;

  private String gender;

}
