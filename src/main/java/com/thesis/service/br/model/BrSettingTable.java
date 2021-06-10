package com.thesis.service.br.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.thesis.service.common.dto.MultiLangDto;
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

  @Type(type = "json")
  private MultiLangDto name;

  private String refTable;

  @Type(type = "list-array")
  @Column(name = "ref_id", columnDefinition = "bigint[]")
  private List<Long> refId;

}
