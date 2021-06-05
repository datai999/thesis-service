package com.thesis.service.br.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.thesis.service.common.dto.MultiLangDto;
import com.thesis.service.common.model.BaseTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "br_const_data", uniqueConstraints = { @UniqueConstraint(columnNames = { "type", "value" }) })
@EqualsAndHashCode(callSuper = true)
public class BrConstDataTable extends BaseTable {

  @NotNull
  private String type;

  @Type(type = "json")
  private MultiLangDto value;

  private Integer no = 0;

  public BrConstDataTable(String id) {
    this.setId(Long.parseLong(id));
  }

  public BrConstDataTable(Object id) {
    this(id.toString());
  }

}
