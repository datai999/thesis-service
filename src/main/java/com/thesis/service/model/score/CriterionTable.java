package com.thesis.service.model.score;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sc_criterion")
public class CriterionTable extends BaseTable {

  // @Type(type = "json")
  // private MultiLangDto name;

  // @OneToOne
  // @JoinColumn(name = "score_method_id")
  // private ConstDataTable scoreMethod;

  // @Type(type = "int-array")
  // @Column(name = "score_percent", columnDefinition = "integer[]")
  // private Collection<Integer> scorePercent;

  // @Type(type = "json")
  // private MultiLangDto description;

}
