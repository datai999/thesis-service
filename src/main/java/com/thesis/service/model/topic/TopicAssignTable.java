package com.thesis.service.model.topic;

import javax.persistence.OneToOne;
import com.thesis.service.model.BaseTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@DynamicUpdate
// @Table(name = "tp_topic_assign")
public class TopicAssignTable extends BaseTable {

  @OneToOne
  private CouncilTable council;

}
