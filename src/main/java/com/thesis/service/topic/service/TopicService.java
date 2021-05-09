package com.thesis.service.topic.service;

import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.repository.TpTopicRepository;

public interface TopicService extends TpTopicRepository {

  TpTopicTable buildFull(TpTopicTable topic);
}
