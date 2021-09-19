package com.thesis.service.service.system;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import com.thesis.service.advice.BusinessException;
import com.thesis.service.constant.SemesterStatus;
import com.thesis.service.dto.system.SemesterResponse;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SemesterService extends ABaseService<SemesterTable, SemesterRepository> {

  @Override
  protected Class<?> getResponseClass() {
    return SemesterResponse.class;
  }

  public SemesterResponse getCurrentSemester() {
    var currentSemester = super.repository.findCurrentSemester();
    return super.mapper.map(currentSemester, SemesterResponse.class);
  }

  public boolean allowStudentRegisterCancelTopic() {
    var currentSemester = super.repository.findCurrentSemester();
    var now = LocalDateTime.now();
    return now.isBefore(currentSemester.getRegisterTopicStart())
        && now.isAfter(currentSemester.getRegisterTopicEnd());
  }

  @Transactional
  public boolean setCurrentSemester(Long semesterId) {

    var nextSemester = super.repository.findById(semesterId).orElseThrow();

    if (StringUtils.isBlank(nextSemester.getName()))
      throw BusinessException.code("semester.001");
    Optional.ofNullable(nextSemester.getRegisterTopicStart())
        .orElseThrow(BusinessException.codeSupplier("semester.002"));
    Optional.ofNullable(nextSemester.getRegisterTopicEnd())
        .orElseThrow(BusinessException.codeSupplier("semester.003"));

    var currentSemester = super.repository.findCurrentSemester();

    nextSemester.setStatus(SemesterStatus.USING);
    currentSemester.setStatus(SemesterStatus.USED);

    super.repository.saveAll(List.of(nextSemester, currentSemester));

    return true;
  }

}
