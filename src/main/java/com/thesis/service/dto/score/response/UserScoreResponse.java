package com.thesis.service.dto.score.response;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.utils.ContextAccessor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserScoreResponse {

  private Long id;
  private String code;
  private String email;
  private String fullName;
  private List<TemplateResponse> templates = List.of();

  @Data
  @EqualsAndHashCode(callSuper = false)
  private static class TemplateResponse extends BaseResponse {
    private Boolean midSemester;
    private Boolean guideTeacher;
    private Boolean reviewTeacher;
    private List<BaseResponse> councilRoles;
    private Boolean numberMark;
    private List<String> scores = List.of();
    private Integer totalScore;

    private Integer stringToInteger(String input) {
      try {
        return Integer.valueOf(input);
      } catch (Exception e) {
        return 0;
      }
    }

    public TemplateResponse(Entry<TemplateTable, List<ScoreTable>> entry) {
      ContextAccessor.getModelConverter().map(entry.getKey(), this);
      this.scores = entry.getValue().stream()
          .map(ScoreTable::getScore).collect(Collectors.toList());
      if (ObjectUtils.defaultIfNull(this.numberMark, false)) {
        this.totalScore = this.scores.stream().map(this::stringToInteger).reduce(0, Integer::sum);
      }
    }
  }

  public UserScoreResponse(UserTable teacher, List<ScoreTable> scores) {
    ContextAccessor.getModelConverter().map(teacher, this);
    if (CollectionUtils.isNotEmpty(scores)) {
      var templateScore = scores.stream()
          .collect(Collectors.groupingBy(ScoreTable::getTemplate));
      this.templates = templateScore.entrySet().stream()
          .map(TemplateResponse::new).collect(Collectors.toList());
    }
  }

  public UserScoreResponse(Entry<UserTable, List<ScoreTable>> entry) {
    this(entry.getKey(), entry.getValue());
  }

}
