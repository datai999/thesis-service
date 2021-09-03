package com.thesis.service.service.topic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.thesis.service.dto.SearchRequest;
import com.thesis.service.model.topic.TpTopicAssignTable;
import com.thesis.service.repository.topic.TpQueryClause;
import com.thesis.service.service.PageService;
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

    // TODO
    // return pageService.search(
    // requestBody,
    // DataBaseFieldConst.TOPIC_ASSIGN_ENTITY,
    // TpTopicAssignTable.class,
    // topicAssignService,
    // selectClause, groupClause);
    return null;
  }

}
