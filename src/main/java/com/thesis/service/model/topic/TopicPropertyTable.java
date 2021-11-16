package com.thesis.service.model.topic;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "tp_topic_property")
public class TopicPropertyTable extends BaseTable {

  @ManyToOne
  private TopicTable topic;

  @ManyToOne
  private MajorTable major;

  @ManyToOne
  private EducationMethodTable educationMethod;

}
