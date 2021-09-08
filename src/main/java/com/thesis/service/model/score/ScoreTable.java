package com.thesis.service.model.score;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.person.StudentTable;
import com.thesis.service.model.person.TeacherTable;
import com.thesis.service.model.topic.TopicAssignTable;
import org.hibernate.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sc_score")
public class ScoreTable extends BaseTable {

  @OneToOne
  @JoinColumn(name = "topic_assign_id")
  private TopicAssignTable topicAssign;

  @OneToOne
  @JoinColumn(name = "criterion_template_id")
  private CriterionTemplateTable criterionTemplate;

  @OneToOne
  private TeacherTable teacher;

  @OneToOne
  private StudentTable student;

  @Type(type = "int-array")
  @Column(name = "score", columnDefinition = "integer[]")
  private Collection<Integer> score;

  @Type(type = "text")
  private String comment;

}
