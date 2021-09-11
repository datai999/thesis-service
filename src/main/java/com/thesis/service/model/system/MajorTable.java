package com.thesis.service.model.system;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.topic.TopicTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "sy_major")
public class MajorTable extends BaseTable {

  private String name;

  @ManyToMany
  @JoinTable(name = "tp_topic_property",
      joinColumns = @JoinColumn(name = "major_id"),
      inverseJoinColumns = @JoinColumn(name = "topic_id"))
  private Set<TopicTable> topics;

  public MajorTable(String id) {
    this.setId(Long.parseLong(id));
  }
}
