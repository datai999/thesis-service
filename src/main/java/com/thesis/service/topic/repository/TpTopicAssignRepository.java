package com.thesis.service.topic.repository;

import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.topic.model.TpTopicAssignTable;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface TpTopicAssignRepository extends BaseRepository<TpTopicAssignTable> {

}
