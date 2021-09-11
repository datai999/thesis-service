package com.thesis.service.model.system;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "sy_subject_department")
public class SubjectDepartmentTable extends BaseTable {

  private String name;

  public SubjectDepartmentTable(String id) {
    this.setId(Long.parseLong(id));
  }
}
