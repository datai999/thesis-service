package com.thesis.service.service.system;

import java.util.List;
import javax.transaction.Transactional;
import com.thesis.service.constant.SemesterStatus;
import com.thesis.service.dto.system.SemesterResponse;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;

@Service
public class SemesterService extends ABaseService<SemesterTable, SemesterRepository> {

  @Override
  protected Class<?> getResponseClass() {
    return SemesterResponse.class;
  }

  @Transactional
  public boolean setCurrentSemester(Long semesterId) {

    var nextSemester = super.repository.findById(semesterId).orElseThrow();
    var currentSemester = super.repository.findCurrentSemester();

    nextSemester.setStatus(SemesterStatus.USING);
    currentSemester.setStatus(SemesterStatus.USED);

    super.repository.saveAll(List.of(nextSemester, currentSemester));

    return true;
  }

}
