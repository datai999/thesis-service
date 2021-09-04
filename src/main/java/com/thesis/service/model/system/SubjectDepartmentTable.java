package com.thesis.service.model.system;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.thesis.service.dto.MultiLangDto;
import com.thesis.service.model.BaseTable;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
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

  @Type(type = "json")
  private MultiLangDto name;

  public SubjectDepartmentTable(String id) {
    this.setId(Long.parseLong(id));
  }
}