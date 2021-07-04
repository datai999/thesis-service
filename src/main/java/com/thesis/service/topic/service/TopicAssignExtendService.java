package com.thesis.service.topic.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

  public Page<TpTopicAssignTable> search(SearchRequest requestBody) {

    Pageable pageable = pageService.getPageable(requestBody);

    @SuppressWarnings("unchecked")
    List<TpTopicAssignTable> result = entityManager
        .createNativeQuery(TpQueryClause.TOPIC_ASSIGN_INNER_JOIN_TOPIC, TpTopicAssignTable.class).getResultList();

    return new PageImpl<>(result, pageable, result.size());
  }

}
