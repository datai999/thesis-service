package com.thesis.service.person.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.thesis.service.model.person.PersonBaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_academy_staff")
public class PsAcademyStaffTable extends PersonBaseTable {

  @Override
  public String getTableName() {
    return "ps_academy_staff";
  }
}
