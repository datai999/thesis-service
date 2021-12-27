package com.thesis.service.service.system;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import javax.transaction.Transactional;
import com.thesis.service.constant.SemesterStatus;
import com.thesis.service.dto.system.SemesterResponse;
import com.thesis.service.model.system.SemesterPropertyTable;
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

  public boolean isBefore(LocalDateTime time) {
    return LocalDateTime.now().minusMinutes(1).isBefore(time);
  }

  public boolean isAfter(LocalDateTime time) {
    return LocalDateTime.now().plusMinutes(1).isAfter(time);
  }

  public boolean isBefore(boolean thesis, Function<SemesterPropertyTable, LocalDateTime> getTime) {
    var currentSemester = super.repository.findCurrentSemester();
    var time = getTime.apply(currentSemester.getProperty(thesis));
    return this.isBefore(time);
  }

  public boolean isAfter(boolean thesis, Function<SemesterPropertyTable, LocalDateTime> getTime) {
    var currentSemester = super.repository.findCurrentSemester();
    var time = getTime.apply(currentSemester.getProperty(thesis));
    return this.isAfter(time);
  }

  public boolean compare(boolean thesis, boolean before,
      Function<SemesterPropertyTable, LocalDateTime> getTime) {
    var currentSemester = super.repository.findCurrentSemester();
    var time = getTime.apply(currentSemester.getProperty(thesis));
    return before ? this.isBefore(time) : this.isAfter(time);
  }

  public boolean nowIn(LocalDateTime from, LocalDateTime to) {
    return this.isAfter(from) && this.isBefore(to);
  }

  public boolean nowIn(boolean thesis,
      Function<SemesterPropertyTable, LocalDateTime> getFromTime,
      Function<SemesterPropertyTable, LocalDateTime> getToTime) {
    var semesterProperty = super.repository.findCurrentSemester().getProperty(thesis);
    return this.nowIn(getFromTime.apply(semesterProperty), getToTime.apply(semesterProperty));
  }

  public boolean inCreateTime(boolean thesis) {
    return this.nowIn(thesis,
        SemesterPropertyTable::getCreateTopicStart,
        SemesterPropertyTable::getCreateTopicEnd);
  }

  public boolean inRegisterTopicTime(boolean thesis) {
    return this.nowIn(thesis,
        SemesterPropertyTable::getRegisterTopicStart,
        SemesterPropertyTable::getRegisterTopicEnd);
  }

  public boolean inMidMarkTime(boolean thesis) {
    return this.nowIn(thesis,
        SemesterPropertyTable::getMidMarkStart,
        SemesterPropertyTable::getMidMarkEnd);
  }

}
