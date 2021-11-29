package com.thesis.service.dto.score.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.dto.user.response.BaseUserResponse;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.utils.ContextAccessor;
import org.apache.commons.lang3.ObjectUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TopicScoreResponse {

  private Long id;
  private String name;
  private List<Teacher> teachers;

  public TopicScoreResponse(TopicTable topic, List<UserTable> teachers, List<ScoreTable> scores) {
    var teacherScore = ObjectUtils.defaultIfNull(scores, new ArrayList<ScoreTable>())
        .stream().collect(Collectors.groupingBy(ScoreTable::getTeacher));
    this.teachers = teachers.stream()
        .map(teacher -> new Teacher(topic, teacher, teacherScore.get(teacher)))
        .collect(Collectors.toList());
  }

  @Data
  @EqualsAndHashCode(callSuper = false)
  private static class Teacher extends BaseUserResponse {
    private List<Student> students;

    public Teacher(TopicTable topic, UserTable teacher, List<ScoreTable> scores) {
      ContextAccessor.getModelConverter().map(teacher, this);
      var studentScore = ObjectUtils.defaultIfNull(scores, new ArrayList<ScoreTable>())
          .stream().collect(Collectors.groupingBy(ScoreTable::getStudent));
      this.students = topic.getTopicStudents().stream()
          .map(e -> new Student(e, studentScore.get(e))).collect(Collectors.toList());
    }
  }

  @Data
  @EqualsAndHashCode(callSuper = false)
  private static class Student extends BaseUserResponse {
    private List<Template> templates;

    public Student(UserTable student, List<ScoreTable> scores) {
      ContextAccessor.getModelConverter().map(student, this);
      var templateScore = ObjectUtils.defaultIfNull(scores, new ArrayList<ScoreTable>())
          .stream().collect(Collectors.groupingBy(ScoreTable::getTemplate));
      this.templates = templateScore.entrySet().stream()
          .sorted((a, b) -> a.getKey().getId().compareTo(b.getKey().getId()))
          .map(Template::new).collect(Collectors.toList());
    }
  }

  @Data
  @EqualsAndHashCode(callSuper = false)
  private static class Template extends BaseResponse {
    private Boolean midSemester;
    private Boolean guideTeacher;
    private Boolean reviewTeacher;
    private Boolean numberMark;
    private List<String> scores;
    private Integer totalScore;
    private BigDecimal meanScore;

    private Integer stringToInteger(String input) {
      try {
        return Integer.valueOf(input);
      } catch (Exception e) {
        return 0;
      }
    }

    public Template(Entry<TemplateTable, List<ScoreTable>> entry) {
      ContextAccessor.getModelConverter().map(entry.getKey(), this);
      this.scores = entry.getValue().stream()
          .map(ScoreTable::getScore).collect(Collectors.toList());
      if (ObjectUtils.defaultIfNull(this.numberMark, false)) {
        this.totalScore = this.scores.stream().map(this::stringToInteger).reduce(0, Integer::sum);
        this.meanScore = new BigDecimal(this.totalScore)
            .divide(new BigDecimal(this.scores.size() == 0 ? this.scores.size() : 1));
        this.meanScore = this.meanScore.setScale(2);
      }
    }
  }

}
