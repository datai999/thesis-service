package com.thesis.service.topic.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.thesis.service.common.model.BaseTable;
import com.thesis.service.person.model.PsStudentTable;
import com.thesis.service.person.model.PsTeacherTable;

import org.hibernate.annotations.Type;
import org.springframework.util.CollectionUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "tp_topic_assign")
public class TpTopicAssignTable extends BaseTable {

  @NonNull
  @OneToOne(cascade = { CascadeType.PERSIST })
  @JoinColumn(name = "topic_id", referencedColumnName = "id")
  private TpTopicTable topic;

  @Type(type = "list-array")
  @Column(name = "execute_student_id", columnDefinition = "bigint[]")
  private List<Long> executeStudentId;

  @Type(type = "list-array")
  @Column(name = "guide_teacher_id", columnDefinition = "bigint[]")
  private List<Long> guideTeacherId;

  @OneToOne
  @JoinColumn(name = "review_teacher_id")
  private PsTeacherTable reviewTeacher;

  @OneToOne
  @JoinColumn(name = "council_id")
  private TpCouncilTable council;

  @Transient
  private Set<PsStudentTable> executeStudent;

  @Transient
  private Set<PsTeacherTable> guideTeacher;

  @PrePersist
  private void prePersist() {
    if (!CollectionUtils.isEmpty(this.executeStudent))
      this.executeStudentId = this.executeStudent.stream().map(BaseTable::getId).collect(Collectors.toList());
    if (!CollectionUtils.isEmpty(this.guideTeacher))
      this.guideTeacherId = this.guideTeacher.stream().map(BaseTable::getId).collect(Collectors.toList());
  }

}
