package com.thesis.service.model.score;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.service.dto.MultiLangDto;
import com.thesis.service.model.BaseTable;
import org.hibernate.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sc_criterion_template")
public class CriterionTemplateTable extends BaseTable {

  @Type(type = "json")
  private MultiLangDto name;

  @Type(type = "json")
  private MultiLangDto description;

  @JsonIgnore
  @Type(type = "long-array")
  @Column(name = "criterion_id", columnDefinition = "bigint[]")
  private List<Long> criterionId;

  @Transient
  private List<CriterionTable> criterion;

  @Type(type = "int-array")
  @Column(name = "criterion_score", columnDefinition = "int[]")
  private List<Long> criterionScore;

}
