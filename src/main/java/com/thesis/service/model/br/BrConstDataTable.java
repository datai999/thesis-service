package com.thesis.service.model.br;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;

@Entity
@Table(name = "br_const_data")
@EqualsAndHashCode(callSuper = true)
public class BrConstDataTable extends BrBaseTable {

  @NotNull
  private String type;

  private String value;
}
