package com.thesis.service.model.topic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.user.UserTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "tp_guide_teacher")
public class TopicGuideTeacherTable extends BaseTable {

  @ManyToOne
  private TopicTable topic;

  @ManyToOne
  private UserTable guideTeacher;

  @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
  private Boolean main;

}
