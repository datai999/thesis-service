package com.thesis.service.person.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.model.PersonBaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_student")
public class PsStudentTable extends PersonBaseTable {

  @NotNull
  @Column(unique = true)
  private int studentCode;

  @OneToOne
  @JoinColumn(name = "major_id")
  private BrConstDataTable major;

  @OneToOne
  @JoinColumn(name = "education_method_id")
  private BrConstDataTable educationMethod;

}
