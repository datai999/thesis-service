package com.thesis.service.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;
import com.thesis.service.model.user.NotificationTable;
import com.thesis.service.repository.user.NotificationRepository;
import com.thesis.service.utils.ContextAccessor;
import com.thesis.service.utils.HtmlUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimerNotificationService {

  private final MessageSource messageSource;
  private final NotificationRepository notificationRepository;

  private static enum TYPE {
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

  public void notifyRegisterTopicStart(Date time) {
    var timer = timers.get(TYPE.REGISTER_TOPIC_START);
    // TODO: notify when register topic start time
    var task = new NotificationTask(() -> List.of());
    timer.schedule(task, time);
  }

  // TODO set notify when update current semester
  public void notifyRegisterTopicEnd(Date time) {
    var timer = timers.get(TYPE.REGISTER_TOPIC_END);

    var htmlMessage = HtmlUtil.toATag(
        "/my/topics",
        messageSource.getMessage(
            "message.detail", null,
            LocaleContextHolder.getLocale()));

    var message = messageSource.getMessage(
        "timer.registerTopicEnd", List.of(htmlMessage).toArray(),
        LocaleContextHolder.getLocale());

    var task = new TimerTask() {
      @Override
      public void run() {
        notificationRepository.notifyUserHasTopicInCurrentSemester(message);
      }
    };
    timer.schedule(task, time);
  }

}
