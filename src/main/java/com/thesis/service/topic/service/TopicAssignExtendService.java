package com.thesis.service.topic.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.thesis.service.common.dto.request.SearchRequest;
import com.thesis.service.common.service.PageService;
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

    String selectClause = TpQueryClause.TOPIC_ASSIGN_SEARCH;
    String whereClause = pageService.getSearchWhereQuery(requestBody.getFilter());
    String groupClause = "GROUP BY tPA.id ";
    String orderClause = pageService.getSearchOrderQuery(requestBody.getSort());

    int totalRecord = pageService.getTotalRecord(selectClause, whereClause, groupClause);

    Pageable pageable = pageService.getPageable(requestBody);

    if (totalRecord == 0)
      return new PageImpl<>(List.of(), pageable, totalRecord);

    String queryClause =
        new StringBuilder(selectClause)
            .append(whereClause)
            .append(groupClause)
            .append(orderClause)
            .toString();
    Query query = entityManager.createNativeQuery(queryClause, TpTopicAssignTable.class);
    query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
    query.setMaxResults(pageable.getPageSize());

    @SuppressWarnings("unchecked")
    List<TpTopicAssignTable> queryResponse = query.getResultList();

    queryResponse = queryResponse.stream().map(topicAssignService::build)
        .collect(Collectors.toList());

    return new PageImpl<>(queryResponse, pageable, totalRecord);
  }



}
