package com.thesis.service.br.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.thesis.service.common.model.BaseTable;

import lombok.EqualsAndHashCode;

@Entity
@Table(name = "br_const_data")
@EqualsAndHashCode(callSuper = true)
public class BrConstDataTable extends BaseTable {

  @NotNull
  private String type;

  private String value;
}
