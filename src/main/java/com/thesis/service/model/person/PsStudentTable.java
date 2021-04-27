package com.thesis.service.model.person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_student")
public class PsStudentTable extends PsBaseTable {
  @Id
  private int studentCode;

  private int majorId;

  private int educationMethodId;
}
