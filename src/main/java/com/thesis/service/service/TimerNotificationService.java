package com.thesis.service.service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.model.user.NotificationTable;
import com.thesis.service.repository.user.NotificationRepository;
import com.thesis.service.utils.ContextAccessor;
import com.thesis.service.utils.TimeConvert;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimerNotificationService {

  private final NotificationRepository notificationRepository;
  private final MessageSourceService messageSourceService;

  private Timer registerTopicEndTimer = new Timer();

  @AllArgsConstructor
  public static class NotificationTask extends TimerTask {

    Supplier<List<NotificationTable>> findNotification = () -> List.of();

    @Override
    public void run() {
      ContextAccessor.getBean(NotificationRepository.class).saveAll(findNotification.get());
    }
  }

  // TODO: notify outline and filter user
  public void notifyRegisterTopicEnd(SemesterTable semester) {
    var time = TimeConvert.toDate(semester.getThesis().getRegisterTopicEnd());
    var timelineViewTag = messageSourceService.dashboardTag(
        messageSourceService.getMessage(MessageCode.Timer.TIMELINE_VIEW));
    var studentMessage = messageSourceService.getMessage(
        MessageCode.Timer.REGISTER_TOPIC_END,
        messageSourceService.topicExecuteTag(),
        timelineViewTag);
    var teacherMessage = messageSourceService.getMessage(
        MessageCode.Timer.REGISTER_TOPIC_END,
        messageSourceService.topicGuideTag(),
        timelineViewTag);
    var task = new TimerTask() {
      @Override
      public void run() {
        notificationRepository.notifyUserHasTopicInCurrentSemester(studentMessage, teacherMessage);
      }
    };
    this.registerTopicEndTimer = new Timer();
    this.registerTopicEndTimer.schedule(task, time);
  }

}
