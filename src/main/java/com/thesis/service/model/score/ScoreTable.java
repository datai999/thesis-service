package com.thesis.service.model.score;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "sc_score")
public class ScoreTable extends BaseTable {

  @ManyToOne
  private TopicTable topic;

  @ManyToOne
  private CriterionTable criterion;

  @ManyToOne
  private UserTable teacher;

  @ManyToOne
  private UserTable student;

  @ManyToOne
  private TemplateTable role;

  private String score;

  @Type(type = "text")
  private String comment;

}
