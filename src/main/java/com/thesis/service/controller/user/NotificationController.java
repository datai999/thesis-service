package com.thesis.service.controller.user;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.user.NotificationTable;
import com.thesis.service.service.user.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController
    extends ABaseController<NotificationTable, NotificationService> {

  @GetMapping("/my")
  public Object findByRequestUser(@RequestParam(defaultValue = "20") Integer limit) {
    return service.findByRequestUser(limit);
  }

  @PutMapping("/seen/{id}")
  public Object seen(@PathVariable Long id) {
    return super.service.seen(id);
  }

}
