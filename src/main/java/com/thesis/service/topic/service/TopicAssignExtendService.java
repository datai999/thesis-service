package com.thesis.service.topic.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.thesis.service.common.dto.DataBaseFieldConst;
import com.thesis.service.common.dto.request.SearchRequest;
import com.thesis.service.common.service.PageService;
import com.thesis.service.common.utils.ContextHolder;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.repository.TpQueryClause;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicAssignExtendService {

  @PersistenceContext
  private EntityManager entityManager;
  private final PageService pageService;
  private final TopicAssignService topicAssignService;

  public Page<TpTopicAssignTable> search(SearchRequest requestBody) {

    Pageable pageable = pageService.getPageable(requestBody);

    StringBuilder selectClause = new StringBuilder(TpQueryClause.TOPIC_ASSIGN_SEARCH);
    String whereClause = this.getWhereQuery(requestBody.getFilter());
    String groupClause = "GROUP BY tPA.id ";
    String orderClause = this.getOrderQuery(requestBody.getSort());

    StringBuilder queryCount =
        new StringBuilder("SELECT COUNT(*) FROM ( ")
            .append(selectClause)
            .append(whereClause)
            .append(groupClause)
            .append(") main_query");

    Integer totalRecord = Integer.valueOf(
        entityManager.createNativeQuery(queryCount.toString()).getSingleResult().toString());

    if (totalRecord == 0)
      return new PageImpl<>(List.of(), pageable, totalRecord);

    Query query = entityManager.createNativeQuery(
        selectClause.append(whereClause).append(groupClause).append(orderClause).toString(),
        TpTopicAssignTable.class);
    query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
    query.setMaxResults(pageable.getPageSize());

    @SuppressWarnings("unchecked")
    List<TpTopicAssignTable> queryResponse = query.getResultList();

    queryResponse = queryResponse.stream().map(topicAssignService::build)
        .collect(Collectors.toList());

    return new PageImpl<>(queryResponse, pageable, totalRecord);
  }

  private String getWhereQuery(Map<String, Object> fieldFilter) {

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

  private String getOrderQuery(SearchRequest.SortRequest sortRequest) {
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
