package com.thesis.service.topic.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.model.BaseTable;

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

  @Type(type = "long-array")
  @Column(name = "teacher_id", columnDefinition = "bigint[]")
  private Collection<Long> teacherIds;

  @Type(type = "long-array")
  @Column(name = "role_id", columnDefinition = "bigint[]")
  private Set<Long> roleIds;

}
