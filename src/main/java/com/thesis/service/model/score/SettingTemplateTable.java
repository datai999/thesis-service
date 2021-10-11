package com.thesis.service.model.score;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.CouncilRoleTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "sc_setting_template")
public class SettingTemplateTable extends BaseTable {

  @ManyToOne
  private MajorTable major;

  private Boolean thesis;

  @Enumerated(EnumType.STRING)
  private TopicRole topicRole;

  @ManyToOne
  private CouncilRoleTable councilRole;

  @ManyToOne
  private TemplateTable template;

}
