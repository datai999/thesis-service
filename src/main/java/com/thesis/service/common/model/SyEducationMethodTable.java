package com.thesis.service.common.model;

import javax.persistence.Entity;
import javax.persistence.Table;
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
public class SyEducationMethodTable extends BaseTable {

  private String name;

  public SyEducationMethodTable(String id) {
    this.setId(Long.parseLong(id));
  }

}
