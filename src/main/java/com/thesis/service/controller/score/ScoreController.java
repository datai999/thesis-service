package com.thesis.service.controller.score;

import java.util.Set;
import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.service.score.ScoreService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scores")
public class ScoreController extends ABaseController<ScoreTable, ScoreService> {

  @PostMapping("/student")
  public Object getStudentScore(@RequestBody ScoreTable entity) {
    return service.getStudentScore(entity);
  }

  @PostMapping("/teacher")
  public Object getTeacherScore(
      @RequestParam Set<Long> teacherIds,
      @RequestBody ScoreTable entity) {
    return service.getTeacherScore(entity, ObjectUtils.defaultIfNull(teacherIds, Set.of()));
  }

  @GetMapping("/topic/{topicId}")
  public Object getTeacherScore(
      @PathVariable long topicId) {
    return service.getTopicScore(topicId);
  }

}
