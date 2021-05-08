package com.thesis.service.topic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thesis.service.common.model.BaseTable;
import com.thesis.service.person.model.PsTeacherTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tp_topic_assign")
public class TpTopicAssignTable extends BaseTable {

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "topic_id", referencedColumnName = "id")
  private TpTopicTable topic;

  @Type(type = "long-array")
  @Column(name = "execute_student_id", columnDefinition = "bigint[]")
  private Set<Long> executeStudentIds;

  @Type(type = "long-array")
  @Column(name = "guide_teacher_id", columnDefinition = "bigint[]")
  private Set<Long> guideTeacherIds;

  @OneToOne
  @JoinColumn(name = "review_teacher_id")
  private PsTeacherTable reviewTeacher;

  @OneToOne
  @JoinColumn(name = "council_id")
  private TpCouncilTable council;

}
