package com.thesis.service.common.service;

import com.thesis.service.br.repository.BrConstDataRepository;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.person.repository.PsTeacherRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntityService {

  public final BrConstDataRepository constData;
  public final PsTeacherRepository teacher;

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
