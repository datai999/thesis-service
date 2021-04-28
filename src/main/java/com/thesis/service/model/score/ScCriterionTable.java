package com.thesis.service.model.score;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thesis.service.model.br.BrBaseTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sc_criterion")
public class ScCriterionTable extends BrBaseTable {

  @OneToOne
  @JoinColumn(name = "criterion_template_id")
  private ScCriterionTemplateTable criterionTemplate;

  private String criterionName;

  private float priority = 1L;

  @Type(type = "text")
  private String description;

}
