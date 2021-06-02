package com.thesis.service.topic.service;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.thesis.service.br.ConstDataService;
import com.thesis.service.common.service.ABaseService;
import com.thesis.service.person.model.PsTeacherTable;
import com.thesis.service.person.service.TeacherService;
import com.thesis.service.topic.model.TpCouncilTable;
import com.thesis.service.topic.repository.TpCouncilRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouncilService extends ABaseService<TpCouncilTable, TpCouncilRepository> {

  final ConstDataService constDataService;
  final TeacherService teacherService;

  @Override
  public TpCouncilTable build(TpCouncilTable entity) {
    entity.setById(constDataService, "role");
    entity.setById(teacherService, "teacher");

    if (Objects.isNull(entity.getRoleId())) {
      return entity;
    }

    // set arrReacher fit with arrTeacherId
    var teacherMap = entity.getTeacher().parallelStream()
        .collect(Collectors.toMap(PsTeacherTable::getId, Function.identity()));
    entity.getTeacher().clear();
    entity.getTeacherId().forEach(teacherId -> entity.getTeacher().add(teacherMap.get(teacherId)));

    return entity;
  }

}
