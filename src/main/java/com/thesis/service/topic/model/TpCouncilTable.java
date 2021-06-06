package com.thesis.service.topic.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.person.model.PsTeacherTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tp_council")
public class TpCouncilTable extends BaseTable {

  @OneToOne
  @JoinColumn(name = "subject_department_id")
  private BrConstDataTable subjectDepartment;

  private String reserveRoom;

  private LocalDate reserveDate;

  private LocalTime startTime;

  private LocalTime endTime;

  @JsonIgnore
  @Type(type = "list-array")
  @Column(name = "role_id", columnDefinition = "bigint[]")
  private Collection<Long> roleId;

  @Transient
  private List<BrConstDataTable> role;

  @JsonIgnore
  @Type(type = "list-array")
  @Column(name = "teacher_id", columnDefinition = "bigint[]")
  private Collection<Long> teacherId;

  @Transient
  private List<PsTeacherTable> teacher;

}
