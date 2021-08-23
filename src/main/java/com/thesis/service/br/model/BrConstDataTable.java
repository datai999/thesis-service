package com.thesis.service.br.model;

import javax.persistence.Column;
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
@Table(name = "br_const_data",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"type", "value"})})
@EqualsAndHashCode(callSuper = true)
public class BrConstDataTable extends BaseTable {

  @NotNull
  private String type;

  @Type(type = "json")
  private MultiLangDto value;

  @Column(columnDefinition = "INTEGER DEFAULT 0")
  private Integer no;

  @Override
  public String getTableName() {
    return "br_const_data";
  }

  public BrConstDataTable(String id) {
    this.setId(Long.parseLong(id));
  }

  public BrConstDataTable(Object id) {
    this(id.toString());
  }

  public static BrConstDataTable type(String type) {
    var result = new BrConstDataTable();
    result.setType(type);
    return result;
  }

}
