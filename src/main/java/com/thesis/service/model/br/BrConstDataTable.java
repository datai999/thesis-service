package com.thesis.service.model.br;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.thesis.service.dto.MultiLangDto;
import com.thesis.service.model.BaseTable;
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

  @Column(nullable = false)
  private String type;

  @Type(type = "json")
  private MultiLangDto value;

  @Column(columnDefinition = "INTEGER DEFAULT 0")
  private Integer no;

  public BrConstDataTable(String id) {
    this.setId(Long.parseLong(id));
  }

  public BrConstDataTable(Integer id) {
    this(id.toString());
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
