package com.thesis.service.model.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_person")
public class PersonTable extends BaseTable {

  @Column(unique = true)
  private String code;

  private String firstName;

  private String lastName;

  private String email;

  private String phone;

  @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
  private Boolean male;

  public String getFullName() {
    return String.format("%s %s", this.firstName, this.lastName);
  }

  public String getFullCodeName() {
    return String.format("%s - %s", this.getCode(), this.getFullName());
  }

}
