package com.thesis.service.score.model;

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
@Table(name = "sc_criterion_template")
public class ScCriterionTemplateTable extends BaseTable {

  @Type(type = "json")
  private MultiLangDto name;

  @OneToOne
  @JoinColumn(name = "score_type_id")
  private BrConstDataTable scoreType;

  @OneToOne
  @JoinColumn(name = "score_method_id")
  private BrConstDataTable scoreMethod;

  @Type(type = "json")
  private MultiLangDto description;

}
