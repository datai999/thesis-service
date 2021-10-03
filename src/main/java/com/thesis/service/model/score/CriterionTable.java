package com.thesis.service.model.score;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sc_criterion")
public class CriterionTable extends BaseTable {

  @ManyToOne
  private CriterionTemplateTable template;

  private String name;

  @Type(type = "text")
  private String description;

  private Integer displayOrder;

  private Double minScore;

  private Double maxScore;

  private String formula;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  private CriterionTable parent;

  @OneToMany(mappedBy = "parent")
  private List<CriterionTable> children;

}
