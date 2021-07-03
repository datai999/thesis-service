package com.thesis.service.score.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thesis.service.common.model.BaseTable;
import com.thesis.service.person.model.PsStudentTable;
import com.thesis.service.person.model.PsTeacherTable;
import com.thesis.service.topic.model.TpTopicAssignTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sc_score")
public class ScScoreTable extends BaseTable {

  @OneToOne
  @JoinColumn(name = "topic_assign_id")
  private TpTopicAssignTable topicAssign;

  @OneToOne
  @JoinColumn(name = "criterion_template_id")
  private ScCriterionTemplateTable criterionTemplate;

  @OneToOne
  @JoinColumn(name = "teacher_code", referencedColumnName = "code")
  private PsTeacherTable teacher;

  @OneToOne
  @JoinColumn(name = "student_code", referencedColumnName = "code")
  private PsStudentTable student;

  @Type(type = "int-array")
  @Column(name = "score", columnDefinition = "integer[]")
  private Collection<Integer> score;

  @Type(type = "text")
  private String comment;

  @Override
  public String getTableName() {
    return "sc_score";
  }

}
