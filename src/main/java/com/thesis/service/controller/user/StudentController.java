package com.thesis.service.controller.user;

import com.thesis.service.service.user.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @PostMapping("/{userId}/topic-register")
  public Object getTopics(@PathVariable long userId, @RequestParam long topicId) {
    return studentService.registerTopic(userId, topicId);
  }

  @GetMapping("/{userId}/topics")
  public Object getTopics(@PathVariable long userId) {
    return studentService.getTopic(userId);
  }

}
