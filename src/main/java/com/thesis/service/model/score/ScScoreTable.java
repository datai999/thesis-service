package com.thesis.service.model.score;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thesis.service.model.BaseTable;
import com.thesis.service.model.person.PsStudentTable;
import com.thesis.service.model.person.PsTeacherTable;
import com.thesis.service.model.topic.TpTopicTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sc_score")
public class ScScoreTable extends BaseTable {

  @OneToOne
  @JoinColumn(name = "topic_id")
  private TpTopicTable topic;

  @OneToOne
  @JoinColumn(name = "criterion_template_id")
  private ScCriterionTemplateTable criterionTemplate;

  @OneToOne
  @JoinColumn(name = "teacher_id")
  private PsTeacherTable teacher;

  @OneToOne
  @JoinColumn(name = "student_id")
  private PsStudentTable student;

  @Type(type = "long-array")
  @Column(name = "criterion_id", columnDefinition = "bigint[]")
  private Collection<Long> criterionId;

  @Type(type = "int-array")
  @Column(name = "score", columnDefinition = "integer[]")
  private Collection<Integer> scores;

  @Type(type = "text")
  private String comment;

}
