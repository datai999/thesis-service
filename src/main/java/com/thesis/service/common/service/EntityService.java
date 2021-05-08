package com.thesis.service.common.service;

import com.thesis.service.br.repository.BrConstDataRepository;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.person.repository.PsStudentRepository;
import com.thesis.service.person.repository.PsTeacherRepository;
import com.thesis.service.topic.repository.TpTopicAssignRepository;
import com.thesis.service.topic.repository.TpTopicRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntityService {

  public final BrConstDataRepository constData;

  public final PsStudentRepository student;
  public final PsTeacherRepository teacher;

  public final TpTopicRepository topic;
  public final TpTopicAssignRepository topicAssign;

  @SuppressWarnings("unchecked")
  public <T extends BaseTable, E extends BaseRepository<T>> E get(String name) {
    try {
      return (E) this.getClass().getDeclaredField(name).get(this);
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
      e.printStackTrace();
    }
    return null;
  }

}
