package com.thesis.service.model.score;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import org.hibernate.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sc_criterion_template")
public class CriterionTemplateTable extends BaseTable {

  private String name;

  @Type(type = "list-array")
  @Column(name = "score_range", columnDefinition = "text[]")
  private List<String> scoreRange;

  private String description;

  @OneToMany(mappedBy = "template")
  private List<CriterionTable> criterions;

  // void a(){
  // ScriptEngineManager mgr = new ScriptEngineManager();
  // ScriptEngine engine = mgr.getEngineByName("JavaScript");
  // String foo = "40+2";
  // System.out.println(engine.eval(foo));
  // }

}
