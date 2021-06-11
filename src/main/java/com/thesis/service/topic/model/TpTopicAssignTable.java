package com.thesis.service.topic.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.person.model.PsStudentTable;
import com.thesis.service.person.model.PsTeacherTable;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "tp_topic_assign")
public class TpTopicAssignTable extends BaseTable {

  @NotNull
  @OneToOne(cascade = { CascadeType.PERSIST })
  @JoinColumn(name = "topic_id", referencedColumnName = "id")
  private TpTopicTable topic;

  @JsonIgnore
  @Type(type = "list-array")
  @Column(name = "execute_student_code", columnDefinition = "varchar[]")
  private List<String> executeStudentCode;

  @JsonIgnore
  @Type(type = "list-array")
  @Column(name = "guide_teacher_code", columnDefinition = "varchar[]")
  private List<String> guideTeacherCode;

  @JsonIgnore
  @Type(type = "list-array")
  @Column(name = "review_teacher_code", columnDefinition = "varchar[]")
  private List<String> reviewTeacherCode;

  @OneToOne
  @JoinColumn(name = "council_id")
  private TpCouncilTable council;

  @Transient
  private Set<PsStudentTable> executeStudent;

  @Transient
  private Set<PsTeacherTable> guideTeacher;

  @Transient
  private Set<PsTeacherTable> reviewTeacher;

  @Override
  public String getTableName() {
    return "tp_topic_assign";
  }

}
