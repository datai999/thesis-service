package com.thesis.service.br.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.service.common.model.BaseTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "br_setting", uniqueConstraints = { @UniqueConstraint(columnNames = { "name_id" }) })
@EqualsAndHashCode(callSuper = true)
public class BrSettingTable extends BaseTable {

  @OneToOne
  @JoinColumn(name = "name_id")
  private BrConstDataTable name;

  @JsonIgnore
  private String refTable;

  @JsonIgnore
  @Type(type = "list-array")
  @Column(name = "ref_id", columnDefinition = "bigint[]")
  private List<Long> refId;

  @Transient
  private List<? extends BaseTable> setting;

  @Override
  public String getTableName() {
    return "br_setting";
  }

  public BrSettingTable setSingleRefId(Long id) {
    this.refId = List.of(id);
    return this;
  }

  public static BrSettingTable name(BrConstDataTable name) {
    var result = new BrSettingTable();
    result.setName(name);
    return result;
  }

  public <T extends BaseTable> BrSettingTable setRef(T refRecord) {
    this.refTable = refRecord.getTableName();
    if (Objects.isNull(refRecord.getId())) {
      this.refId = null;
    } else {
      this.refId = List.of(refRecord.getId());
    }
    return this;
  }

}
