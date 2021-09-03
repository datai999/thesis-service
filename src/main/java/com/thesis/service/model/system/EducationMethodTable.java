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
@Table(name = "sy_education_method")
public class EducationMethodTable extends BaseTable {

  private String name;

  public EducationMethodTable(String id) {
    this.setId(Long.parseLong(id));
  }

}
