package com.thesis.service.br.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thesis.service.common.model.BaseTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "br_setting")
@EqualsAndHashCode(callSuper = true)
public class BrSettingTable extends BaseTable {

  @OneToOne
  @JoinColumn(name = "name_id")
  private BrConstDataTable name;

  private String refTable;

  @Type(type = "list-array")
  @Column(name = "ref_id", columnDefinition = "bigint[]")
  private List<Long> refId;

  @Override
  public String getTableName() {
    return "br_setting";
  }

  public BrSettingTable setSingleRefId(Long id) {
    this.refId = List.of(id);
    return this;
  }

  public <T extends BaseTable> BrSettingTable(BrConstDataTable name, T refRecord) {
    this.name = name;
    this.refTable = refRecord.getTableName();
    if (Objects.isNull(refRecord.getId())) {
      this.refId = null;
    } else {
      this.refId = List.of(refRecord.getId());
    }

  }
}
