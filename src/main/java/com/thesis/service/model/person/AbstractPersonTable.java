package com.thesis.service.model.person;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import com.thesis.service.model.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class AbstractPersonTable extends BaseTable {

  @OneToOne(optional = false)
  private PersonTable person;

  public String getFullCodeName() {
    return String.format("%s - %s", this.person.getCode(), this.person.getFullName());
  }

}
