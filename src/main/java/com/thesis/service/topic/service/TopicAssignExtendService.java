package com.thesis.service.topic.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.thesis.service.common.dto.DataBaseFieldConst;
import com.thesis.service.common.dto.request.SearchRequest;
import com.thesis.service.common.service.PageService;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.repository.TpQueryClause;
import org.springframework.data.domain.Page;
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
    String groupClause = "GROUP BY tPA.id ";

    return pageService.search(
        requestBody,
        DataBaseFieldConst.TOPIC_ASSIGN_ENTITY,
        TpTopicAssignTable.class,
        topicAssignService,
        selectClause, groupClause);
  }

}
