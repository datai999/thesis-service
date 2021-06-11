package com.thesis.service.br.model;

import java.util.List;

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

  public BrSettingTable setSingleRefId(Long id) {
    this.refId = List.of(id);
    return this;
  }

  public <T extends BaseTable> BrSettingTable(BrConstDataTable name, String refTable, T entity) {
    this.name = name;
    this.refTable = refTable;
    this.setSingleRefId(entity.getId());
  }
}
