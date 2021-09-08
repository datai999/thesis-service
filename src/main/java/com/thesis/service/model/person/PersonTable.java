package com.thesis.service.model.person;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import org.hibernate.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_person")
public class PersonTable extends BaseTable {

  @Column(unique = true)
  private String code;

  private String firstName;

  private String lastName;

  @Column(unique = true)
  private String email;

  private String phone;

  @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
  private Boolean male;

  @Type(type = "list-array")
  @Column(columnDefinition = "text[]")
  private List<String> roles;

  public String getFullName() {
    return String.format("%s %s", this.firstName, this.lastName);
  }

  public String getFullCodeName() {
    return String.format("%s - %s", this.getCode(), this.getFullName());
  }

}
