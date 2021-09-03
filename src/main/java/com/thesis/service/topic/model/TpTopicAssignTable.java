package com.thesis.service.topic.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.model.topic.TpTopicTable;
import com.thesis.service.person.model.PsTeacherTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "tp_topic_assign")
public class TpTopicAssignTable extends BaseTable {

  @OneToOne(cascade = {CascadeType.PERSIST})
  private TpTopicTable topic;

  @OneToMany
  private List<PsTeacherTable> reviewTeachers;

  @OneToOne
  private TpCouncilTable council;

}
