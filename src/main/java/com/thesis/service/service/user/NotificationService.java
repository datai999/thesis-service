package com.thesis.service.service.user;

import java.util.List;
import java.util.stream.Collectors;
import com.thesis.service.model.user.NotificationTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.user.NotificationRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends ABaseService<NotificationTable, NotificationRepository> {

  public void notify(List<UserTable> receiver, String message) {
    if (CollectionUtils.isEmpty(receiver))
      return;
    var notifications = receiver.parallelStream()
        .map(user -> new NotificationTable().setReceiver(user).setMessage(message))
        .collect(Collectors.toList());
    super.repository.saveAll(notifications);
  }

}
