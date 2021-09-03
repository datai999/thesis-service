package com.thesis.service.service.topic;

import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends AbstractBaseService<TopicTable, TopicRepository> {

}
