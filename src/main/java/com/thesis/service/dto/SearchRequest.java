package com.thesis.service.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class SearchRequest {

  private PageSearchRequest page = new PageSearchRequest();
  private SortRequest sort = new SortRequest();
  private Map<String, Object> filter = new HashMap<>();

  @Data
  public static class PageSearchRequest {
    private Integer number = 0;
    private Integer size = 5;
  }

  @Data
  public static class SortRequest {
    private String field;
    private Boolean descend = true;
  }
}
