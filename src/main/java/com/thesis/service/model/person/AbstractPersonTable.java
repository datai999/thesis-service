package com.thesis.service.model.person;

import java.util.Objects;
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
  private PersonTable person = new PersonTable();

  public String getCode() {
    return this.person.getCode();
  }

  public String getFirstName() {
    return this.person.getFirstName();
  }

  public String getLastName() {
    return this.person.getLastName();
  }

  public String getGender() {
    return this.person.isMale() ? "Nam" : "Nữ";
  }

  public String getEmail() {
    return this.person.getEmail();
  }

  public String getPhone() {
    return this.person.getPhone();
  }

  public String getFullCodeName() {
    return Objects.isNull(this.person) ? null
        : String.format("%s - %s", this.person.getCode(), this.person.getFullName());
  }

}
