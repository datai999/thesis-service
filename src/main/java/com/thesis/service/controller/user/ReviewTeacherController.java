package com.thesis.service.controller.user;

import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.service.user.ReviewTeacherService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/review-teachers")
@RequiredArgsConstructor
public class ReviewTeacherController {

  private final ReviewTeacherService reviewTeacherService;

  @GetMapping("/{subjectDepartmentId}/topic-review")
  public Object getTopicReview(
      @PathVariable long subjectDepartmentId,
      @RequestParam String semesterName) {
    return reviewTeacherService.getTopicReview(subjectDepartmentId, semesterName);
  }

  @GetMapping("/{userId}/topics")
  public Object getTopics(@PathVariable long userId,
      @RequestParam String semesterName) {
    return reviewTeacherService.getTopic(userId, semesterName);
  }

  @PostMapping("/assign")
  public Object findByExample(@RequestBody TopicTable entity) {
    return reviewTeacherService.assignReview(entity);
  }

  @PostMapping("/get")
  public Object getTeacher(
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort,
      @RequestBody UserTable entity) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return reviewTeacherService.getTeacher(entity, sortable);
  }

}
