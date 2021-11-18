package com.thesis.service.controller.user;

import com.thesis.service.service.user.GuideTeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/guide-teachers")
@RequiredArgsConstructor
public class GuideTeacherController {

  private final GuideTeacherService guideTeacherService;

  @GetMapping("/{userId}/topics")
  public Object getTopics(@PathVariable long userId,
      @RequestParam String semesterName) {
    return guideTeacherService.getTopic(userId, semesterName);
  }

}
