package com.thesis.service.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.model.user.NotificationTable;
import com.thesis.service.repository.user.NotificationRepository;
import com.thesis.service.utils.ContextAccessor;
import com.thesis.service.utils.HtmlUtil;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimerNotificationService {

  private final NotificationRepository notificationRepository;
  private final MessageSourceService messageSourceService;

  private enum TYPE {
    REGISTER_TOPIC_START, REGISTER_TOPIC_END
  }

  private static final Map<TYPE, Timer> timers = Map.of(
      TYPE.REGISTER_TOPIC_START, new Timer(), TYPE.REGISTER_TOPIC_END, new Timer());

  @AllArgsConstructor
  public static class NotificationTask extends TimerTask {

    Supplier<List<NotificationTable>> findNotification = () -> List.of();

    @Override
    public void run() {
      ContextAccessor.getBean(NotificationRepository.class).saveAll(findNotification.get());
    }
  }

  public void notifyRegisterTopicEnd(Date time) {
    var studentMessage = messageSourceService.getMessage(
        MessageCode.Timer.REGISTER_TOPIC_END, HtmlUtil.topicExecuteTag());
    var teacherMessage = messageSourceService.getMessage(
        MessageCode.Timer.REGISTER_TOPIC_END, HtmlUtil.topicGuideTag());
    var task = new TimerTask() {
      @Override
      public void run() {
        notificationRepository.notifyUserHasTopicInCurrentSemester(studentMessage, teacherMessage);
      }
    };
    timers.get(TYPE.REGISTER_TOPIC_END).schedule(task, time);
  }

}
