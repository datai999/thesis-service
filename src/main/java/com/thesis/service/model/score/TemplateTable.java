package com.thesis.service.model.score;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.CouncilRoleTable;
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
@Table(name = "sc_template")
public class TemplateTable extends BaseTable {

  private String name;

  private Boolean numberMark;

  @Type(type = "text")
  private String description;

  @OneToOne
  private CriterionTable rootCriterion;

  private Boolean thesis;

  @ManyToMany
  @JoinTable(
      joinColumns = @JoinColumn(name = "template_id"),
      inverseJoinColumns = @JoinColumn(name = "major_id"))
  private List<MajorTable> majors;

  @Enumerated(EnumType.STRING)
  private TopicRole topicRole;

  @ManyToOne
  private CouncilRoleTable councilRole;

}
