package com.thesis.service.score.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.dto.MultiLangDto;
import com.thesis.service.common.model.BaseTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sc_criterion")
public class ScCriterionTable extends BaseTable {

  @Type(type = "json")
  private MultiLangDto name;

  @OneToOne
  @JoinColumn(name = "score_method_id")
  private BrConstDataTable scoreMethod;

  @Type(type = "int-array")
  @Column(name = "score_percent", columnDefinition = "integer[]")
  private Collection<Integer> scorePercent;

  @Type(type = "json")
  private MultiLangDto description;

  @Override
  public String getTableName() {
    return "sc_criterion";
  }

}
