package com.thesis.service.common.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.thesis.service.br.model.BrConstDataTable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class PersonBaseTable extends BaseTable {

  @NotNull
  @Column(unique = true)
  private String code;

  @NotBlank
  private String name;

  private String email;

  private String phone;

  @OneToOne
  @JoinColumn(name = "gender_id")
  private BrConstDataTable gender;
}
