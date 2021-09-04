package com.thesis.service.model.person;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_academy_staff")
public class AcademyStaffTable extends PersonBaseTable {

  @Override
  public String getTableName() {
    return "ps_academy_staff";
  }
}