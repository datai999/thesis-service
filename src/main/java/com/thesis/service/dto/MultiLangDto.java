package com.thesis.service.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MultiLangDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String en;
  private String vi;
}
