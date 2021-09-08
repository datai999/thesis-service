package com.thesis.service.model.br;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.service.model.BaseTable;
import org.hibernate.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "br_setting", uniqueConstraints = {@UniqueConstraint(columnNames = {"name_id"})})
@EqualsAndHashCode(callSuper = true)
public class SettingTable extends BaseTable {

  @OneToOne
  @JoinColumn(name = "name_id")
  private ConstDataTable name;

  @JsonIgnore
  private String refTable;

  @JsonIgnore
  @Type(type = "list-array")
  @Column(name = "ref_id", columnDefinition = "bigint[]")
  private List<Long> refId;

  @Transient
  private List<? extends BaseTable> setting;

  public SettingTable setSingleRefId(Long id) {
    this.refId = List.of(id);
    return this;
  }

  public static SettingTable name(ConstDataTable name) {
    var result = new SettingTable();
    result.setName(name);
    return result;
  }

}
