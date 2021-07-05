package com.thesis.service.score.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.service.common.dto.MultiLangDto;
import com.thesis.service.common.model.BaseTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sc_criterion_template")
public class ScCriterionTemplateTable extends BaseTable {

  @Type(type = "json")
  private MultiLangDto name;

  @Type(type = "json")
  private MultiLangDto description;

  @JsonIgnore
  @Type(type = "long-array")
  @Column(name = "criterion_id", columnDefinition = "bigint[]")
  private List<Long> criterionId;

  @Transient
  private List<ScCriterionTable> criterion;

  @Type(type = "int-array")
  @Column(name = "criterion_score", columnDefinition = "int[]")
  private List<Long> criterionScore;

  @Override
  public String getTableName() {
    return "sc_criterion_template";
  }

}
