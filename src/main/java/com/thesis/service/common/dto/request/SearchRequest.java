package com.thesis.service.common.dto.request;

import java.util.Map;

import lombok.Data;

@Data
public class SearchRequest {

  private PageRequest page = new PageRequest();
  private SortRequest sort;
  private Map<String, Object> filter;

  @Data
  public static class PageRequest {
    private Integer number = 0;
    private Integer size = 5;
  }

  @Data
  public static class SortRequest {
    private String field;
    private Boolean descend = true;
  }
}
