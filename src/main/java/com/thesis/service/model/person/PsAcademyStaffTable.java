package com.thesis.service.model.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_academy_staff")
public class PsAcademyStaffTable extends PsBaseTable {
  @Id
  @GeneratedValue
  private int id;
}
