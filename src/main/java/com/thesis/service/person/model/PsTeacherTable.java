package com.thesis.service.person.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.model.PersonBaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_teacher")
public class PsTeacherTable extends PersonBaseTable {

  @OneToOne
  @JoinColumn(name = "subject_department_id")
  private BrConstDataTable subjectDepartment;

  @OneToOne
  @JoinColumn(name = "degree_id")
  private BrConstDataTable degree;
}
