package com.thesis.service.model.topic;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.score.TemplateTable;
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

  @OneToMany(mappedBy = "councilRole")
  private List<TemplateTable> templates;

}
