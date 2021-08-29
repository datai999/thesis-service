package com.thesis.service.topic.service;

import com.thesis.service.common.service.AbstractBaseService;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.repository.TpTopicRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends AbstractBaseService<TpTopicTable, TpTopicRepository> {

}
