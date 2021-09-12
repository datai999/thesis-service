package com.thesis.service.model.system;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.topic.TopicTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "sy_education_method")
public class EducationMethodTable extends BaseTable {

  private String name;

  @ManyToMany(mappedBy = "educationMethods")
  private Set<TopicTable> topics;

  public EducationMethodTable(String id) {
    this.setId(Long.parseLong(id));
  }

}
