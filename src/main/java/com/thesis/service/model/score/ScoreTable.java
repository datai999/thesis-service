package com.thesis.service.model.score;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.topic.CouncilRoleTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "sc_score")
public class ScoreTable extends BaseTable {

  @ManyToOne
  private TopicTable topic;

  @ManyToOne
  private UserTable teacher;

  @ManyToOne
  private UserTable student;

  @ManyToOne
  private TemplateTable template;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  private CriterionTable criterion;

  private Boolean guideTeacher;

  private Boolean reviewTeacher;

  @ManyToOne
  private CouncilRoleTable councilRole;

  private String score;

  @Type(type = "text")
  private String comment;

}
