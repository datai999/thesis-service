package com.thesis.service.service.user;

import java.util.List;
import java.util.stream.Collectors;
import com.thesis.service.dto.user.response.NotificationResponse;
import com.thesis.service.dto.user.response.UserNotificationResponse;
import com.thesis.service.model.user.NotificationTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.user.NotificationRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends ABaseService<NotificationTable, NotificationRepository> {

  @Override
  protected Class<?> getResponseClass() {
    return NotificationResponse.class;
  }

  public void notify(List<UserTable> receiver, String message) {
    if (CollectionUtils.isEmpty(receiver))
      return;
    var notifications = receiver.parallelStream()
        .map(user -> new NotificationTable().setReceiver(user).setSeen(false).setMessage(message))
        .collect(Collectors.toList());
    super.repository.saveAll(notifications);
  }

  public UserNotificationResponse findByRequestUser(int limit) {
    var all = super.repository.findByIdDesc(super.getAuth().getId(), limit);
    var unseen = super.repository.findByUnseenIdDesc(super.getAuth().getId(), limit);
    return new UserNotificationResponse()
        .setAll(super.mapper.map(all, NotificationResponse.class))
        .setUnseen(super.mapper.map(unseen, NotificationResponse.class));
  }

  public Object seen(Long id) {
    var notification = super.repository.findById(id).orElseThrow();
    super.repository.save(notification.setSeen(true));
    return true;
  }

}
