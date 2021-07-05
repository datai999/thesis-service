package com.thesis.service.common.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.thesis.service.common.dto.request.SearchRequest;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.utils.ContextHolder;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageService {

  @PersistenceContext
  private EntityManager entityManager;

  public Pageable getPageable(SearchRequest searchRequest, Map<String, String> dbFieldNameMap) {

    // map sort
    String sortEntityField = searchRequest.getSort().getField();
    if (Objects.nonNull(sortEntityField)) {
      sortEntityField = ObjectUtils.defaultIfNull(
          dbFieldNameMap.get(sortEntityField),
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
            .append(selectClause).append(" ")
            .append(whereClause).append(" ")
            .append(groupClause).append(" ")
            .append(") main_query");

    return Integer.valueOf(
        entityManager.createNativeQuery(queryCount.toString()).getSingleResult().toString());
  }

  public String getSearchWhereQuery(Map<String, Object> fieldFilter,
      Map<String, String> dbFieldNameMap) {

    if (fieldFilter.isEmpty())
      return "";

    var filterQuery = new StringBuilder();
    fieldFilter.keySet().stream().forEach(filterField -> {
      String filterEntityField =
          String.format(dbFieldNameMap.get(filterField), ContextHolder.getLang());
      filterQuery.append("AND ").append(filterEntityField).append("::::TEXT ILIKE '%")
          .append(fieldFilter.get(filterField))
          .append("%' ");
    });

    return String.format("WHERE %s", filterQuery.substring(4));
  }

  public String getSearchOrderQuery(SearchRequest.SortRequest sortRequest,
      Map<String, String> dbFieldNameMap) {

    if (Objects.isNull(sortRequest.getField()))
      return "";

    String sortEntityField = String.format(dbFieldNameMap.get(sortRequest.getField()),
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



  public <E extends BaseTable> Page<E> search(
      SearchRequest requestBody,
      Map<String, String> dbFieldNameMap,
      Class<E> response,
      IService<E> buildService,
      String selectClause,
      String groupClause) {

    String whereClause = this.getSearchWhereQuery(requestBody.getFilter(), dbFieldNameMap);
    String orderClause = this.getSearchOrderQuery(requestBody.getSort(), dbFieldNameMap);

    int totalRecord = this.getTotalRecord(selectClause, whereClause, groupClause);

    Pageable pageable = this.getPageable(requestBody, dbFieldNameMap);

    if (totalRecord == 0)
      return new PageImpl<>(List.of(), pageable, totalRecord);

    String queryClause =
        new StringBuilder(selectClause).append(" ")
            .append(whereClause).append(" ")
            .append(groupClause).append(" ")
            .append(orderClause).append(" ")
            .toString();

    Query query = entityManager.createNativeQuery(queryClause, response);
    query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
    query.setMaxResults(pageable.getPageSize());

    @SuppressWarnings("unchecked")
    List<E> queryResponse = query.getResultList();

    queryResponse = queryResponse.stream().map(buildService::build)
        .collect(Collectors.toList());

    return new PageImpl<>(queryResponse, pageable, totalRecord);
  }

}
