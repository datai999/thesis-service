package com.thesis.service.controller.user;

import com.thesis.service.service.user.StudentService;
import org.springframework.web.bind.annotation.DeleteMapping;
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

  @GetMapping("/{userId}/topics")
  public Object getTopics(@PathVariable long userId) {
    return studentService.getTopic(userId);
  }

  @GetMapping("/{userId}/allow-register-topic")
  public Object allowRegisterTopic(@PathVariable long userId) {
    return studentService.allowRegisterTopic(userId);
  }

  @GetMapping("/{userId}/done-outline")
  public Object doneOutline(@PathVariable long userId) {
    return studentService.doneOutline(userId);
  }

  @PostMapping("/{userId}/topic-register")
  public Object getTopics(@PathVariable long userId, @RequestParam long topicId) {
    return studentService.registerTopic(userId, topicId);
  }

  @DeleteMapping("/{userId}/topic-cancel")
  public Object studentCancel(@PathVariable long userId, @RequestParam long topicId) {
    return studentService.cancelTopic(userId, topicId);
  }

}
