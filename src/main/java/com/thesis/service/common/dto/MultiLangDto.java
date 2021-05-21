package com.thesis.service.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MultiLangDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String en;
  private String vi;
}