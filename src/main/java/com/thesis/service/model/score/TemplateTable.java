package com.thesis.service.model.score;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
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

  @Type(type = "text")
  private String description;

  @OneToOne
  private CriterionTable rootCriterion;

}
