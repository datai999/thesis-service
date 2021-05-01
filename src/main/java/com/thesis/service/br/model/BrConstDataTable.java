package com.thesis.service.br.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.thesis.service.common.model.BaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "br_const_data", uniqueConstraints = { @UniqueConstraint(columnNames = { "type", "value" }) })
@EqualsAndHashCode(callSuper = true)
@Data
public class BrConstDataTable extends BaseTable {

  @NotNull
  private String type;

  private String value;
}
