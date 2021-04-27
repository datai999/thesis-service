package com.thesis.service.model.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thesis.service.model.br.BrConstDataTable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_teacher")
public class PsTeacherTable extends PsBaseTable {
  @Id
  @GeneratedValue
  private int id;

  @OneToOne
  @JoinColumn(name = "subject_department_id")
  private BrConstDataTable subjectDepartment;

  @OneToOne
  @JoinColumn(name = "degree_id")
  private BrConstDataTable degree;
}
