package com.thesis.service.common.service;

import java.util.Objects;

import com.thesis.service.common.dto.DataBaseFieldConst;
import com.thesis.service.common.dto.request.SearchRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageService {

  public Pageable getPageable(SearchRequest searchRequest) {
    // map sort
    String sortEntityField = DataBaseFieldConst.ENTITY.get(searchRequest.getSort().getField());
    if (Objects.isNull(sortEntityField)) {
      sortEntityField = searchRequest.getSort().getField();
    }

    Pageable pageable = PageRequest.of(searchRequest.getPage().getNumber(), searchRequest.getPage().getSize());

    if (Objects.nonNull(sortEntityField)) {
      Sort sortable = Sort.by(sortEntityField);
      sortable = searchRequest.getSort().getDescend() ? sortable.descending() : sortable.ascending();
      pageable = PageRequest.of(searchRequest.getPage().getNumber(), searchRequest.getPage().getSize(), sortable);
    }

    // map filter
    searchRequest.getFilter().keySet().parallelStream().forEach(filterField -> {
      String filterEntityField = DataBaseFieldConst.ENTITY.get(filterField);
      searchRequest.getFilter().put(filterEntityField, searchRequest.getFilter().get(filterField));
      searchRequest.getFilter().remove(filterField);
    });

    return pageable;
  }

}
