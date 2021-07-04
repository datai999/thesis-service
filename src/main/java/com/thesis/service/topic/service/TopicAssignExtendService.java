package com.thesis.service.topic.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

  public Page<TpTopicAssignTable> search(SearchRequest requestBody) {

    var query = new StringBuilder(TpQueryClause.TOPIC_ASSIGN_INNER_JOIN_TOPIC);

    query.append(getFilterQuery(requestBody.getFilter()));

    Pageable pageable = pageService.getPageable(requestBody);

    @SuppressWarnings("unchecked")
    List<TpTopicAssignTable> result = entityManager.createNativeQuery(query.toString(), TpTopicAssignTable.class)
        .getResultList();

    return new PageImpl<>(result, pageable, result.size());
  }

  private String getFilterQuery(Map<String, Object> fieldFilter) {

    if (fieldFilter.isEmpty())
      return "";

    String lang = ContextHolder.getLang();

    var filterQuery = new StringBuilder();
    fieldFilter.keySet().parallelStream().forEach(filterField -> {
      String filterEntityField = String.format(DataBaseFieldConst.ENTITY.get(filterField), lang);
      filterQuery.append("AND ").append(filterEntityField).append(" ILIKE '%").append(fieldFilter.get(filterField))
          .append("%' ");
    });

    return "WHERE " + filterQuery.substring(4);
  }

}
