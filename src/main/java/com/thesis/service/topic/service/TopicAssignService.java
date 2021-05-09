package com.thesis.service.topic.service;

import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.repository.TpTopicAssignRepository;

public interface TopicAssignService extends TpTopicAssignRepository {

  TpTopicAssignTable buildFull(TpTopicAssignTable topicAssign);

}
