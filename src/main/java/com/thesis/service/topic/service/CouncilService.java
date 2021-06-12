package com.thesis.service.topic.service;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.thesis.service.br.service.ConstDataService;
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
  public void preBuild(TpCouncilTable entity) {
    entity.setById(constDataService, "role");
    entity.setById(teacherService, "teacher");

    if (Objects.isNull(entity.getTeacherCode())) {
      return;
    }

    // set arrTeacher fit with arrTeacherCode
    var teacherMap = entity.getTeacher().parallelStream()
        .collect(Collectors.toMap(PsTeacherTable::getCode, Function.identity()));
    entity.getTeacher().clear();
    entity.getTeacherCode().forEach(teacherCode -> entity.getTeacher().add(teacherMap.get(teacherCode)));
  }

}
