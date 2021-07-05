package com.thesis.service.common.service;

import java.util.Map;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.thesis.service.common.dto.DataBaseFieldConst;
import com.thesis.service.common.dto.request.SearchRequest;
import com.thesis.service.common.utils.ContextHolder;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageService {

  @PersistenceContext
  private EntityManager entityManager;

  public Pageable getPageable(SearchRequest searchRequest) {

    // map sort
    String sortEntityField = searchRequest.getSort().getField();
    if (Objects.nonNull(sortEntityField)) {
      sortEntityField = ObjectUtils.defaultIfNull(
          DataBaseFieldConst.ENTITY.get(sortEntityField),
          sortEntityField);
    }

    Pageable pageable = PageRequest.of(
        searchRequest.getPage().getNumber(),
        searchRequest.getPage().getSize());

    if (Objects.nonNull(sortEntityField)) {
      Sort sortable = Sort.by(sortEntityField);
      sortable =
          searchRequest.getSort().getDescend() ? sortable.descending() : sortable.ascending();
      pageable = PageRequest.of(
          searchRequest.getPage().getNumber(),
          searchRequest.getPage().getSize(), sortable);
    }

    return pageable;
  }

  public int getTotalRecord(String selectClause, String whereClause, String groupClause) {
    StringBuilder queryCount =
        new StringBuilder("SELECT COUNT(*) FROM ( ")
            .append(selectClause)
            .append(whereClause)
            .append(groupClause)
            .append(") main_query");

    return Integer.valueOf(
        entityManager.createNativeQuery(queryCount.toString()).getSingleResult().toString());
  }

  public String getSearchWhereQuery(Map<String, Object> fieldFilter) {

    if (fieldFilter.isEmpty())
      return "";

    var filterQuery = new StringBuilder();
    fieldFilter.keySet().stream().forEach(filterField -> {
      String filterEntityField =
          String.format(DataBaseFieldConst.ENTITY.get(filterField), ContextHolder.getLang());
      filterQuery.append("AND ").append(filterEntityField).append("::::TEXT ILIKE '%")
          .append(fieldFilter.get(filterField))
          .append("%' ");
    });

    return String.format("WHERE %s", filterQuery.substring(4));
  }

  public String getSearchOrderQuery(SearchRequest.SortRequest sortRequest) {

    if (Objects.isNull(sortRequest.getField()))
      return "";

    String sortEntityField = String.format(DataBaseFieldConst.ENTITY.get(sortRequest.getField()),
        ContextHolder.getLang());

    var orderClause = new StringBuilder("ORDER BY ");

    // FIXME order by sum record field
    // orderClause.append(String.format(
    // "CARDINALITY(ARRAY_REMOVE(ARRAY_AGG(%s), NULL)) %s",
    // sortEntityField, sortRequest.getDescend() ? "DESC" : "ASC"));

    orderClause.append(String.format(
        " (ARRAY_AGG(%s))[1] %s",
        sortEntityField, !sortRequest.getDescend() ? "DESC" : "ASC"));

    return orderClause.toString();
  }


}
