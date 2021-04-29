package com.thesis.service.model.score;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thesis.service.model.BaseTable;
import com.thesis.service.model.br.BrConstDataTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sc_criterion_template")
public class ScCriterionTemplateTable extends BaseTable {

  @OneToOne
  @JoinColumn(name = "score_type_id")
  private BrConstDataTable scoreType;

  @OneToOne
  @JoinColumn(name = "score_method_id")
  private BrConstDataTable scoreMethod;

  @Type(type = "text")
  private String description;

}
