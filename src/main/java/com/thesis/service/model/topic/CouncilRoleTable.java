package com.thesis.service.model.topic;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tp_council_role")
public class CouncilRoleTable extends BaseTable {

  private String name;

  private Integer min;

  private Integer max;

  private Integer displayOrder;

}
