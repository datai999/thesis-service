package com.thesis.service.service.system;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import com.thesis.service.constant.SemesterStatus;
import com.thesis.service.dto.system.SemesterResponse;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.TimerNotificationService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SemesterService extends ABaseService<SemesterTable, SemesterRepository> {

  private final TimerNotificationService timerNotificationService;

  @Override
  protected Class<?> getResponseClass() {
    return SemesterResponse.class;
  }

  public SemesterTable getCurrentSemester() {
    return super.repository.findCurrentSemester();
  }

  @Transactional
  public boolean setCurrentSemester(Long semesterId) {

    var nextSemester = super.repository.findById(semesterId).orElseThrow();
    var currentSemester = super.repository.findCurrentSemester();

    nextSemester.setStatus(SemesterStatus.USING);
    currentSemester.setStatus(SemesterStatus.USED);

    super.repository.saveAll(List.of(nextSemester, currentSemester));

    timerNotificationService.notifyRegisterTopicEnd(nextSemester);

    return true;
  }

  @Override
  public Object update(SemesterTable entity) {
    var existEntity = this.repository.findById(entity.getId()).orElseThrow();
    entity.setCreatedAt(existEntity.getCreatedAt());
    var response = this.repository.save(entity);
    if (SemesterStatus.USING.equals(entity.getStatus())) {
      timerNotificationService.notifyRegisterTopicEnd(response);
    }
    return this.map(response);
  }

  private boolean isAfter(LocalDateTime time) {
    return LocalDateTime.now().plusMinutes(1).isAfter(time);
  }

  private boolean nowIn(LocalDateTime from, LocalDateTime to) {
    var now = LocalDateTime.now();
    return this.isAfter(from) && now.minusMinutes(1).isBefore(to);
  }

  public boolean inCreateTime(boolean thesis) {
    var currentSemester = super.repository.findCurrentSemester();
    return this.nowIn(currentSemester.getProperty(thesis).getRegisterTopicStart(),
        currentSemester.getProperty(thesis).getRegisterTopicEnd());
  }

  public boolean inAnyCreateTime() {
    return this.inCreateTime(false) || this.inCreateTime(true);
  }

  public boolean inRegisterTopicTime(boolean thesis) {
    var currentSemester = super.repository.findCurrentSemester();
    var semesterProperty = currentSemester.getProperty(thesis);
    return this.nowIn(semesterProperty.getRegisterTopicStart(),
        semesterProperty.getRegisterTopicEnd());
  }

  public boolean beforeMidMarkStartTime(boolean thesis) {
    var currentSemester = super.repository.findCurrentSemester();
    return this.isAfter(currentSemester.getProperty(thesis).getMidMarkStart());
  }

  public boolean beforeMidMarkEndTime(boolean thesis) {
    var currentSemester = super.repository.findCurrentSemester();
    return this.isAfter(currentSemester.getProperty(thesis).getMidMarkEnd());
  }

}
