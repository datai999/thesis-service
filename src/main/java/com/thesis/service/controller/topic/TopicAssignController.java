package com.thesis.service.controller.topic;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.dto.SearchRequest;
import com.thesis.service.model.topic.TpTopicAssignTable;
import com.thesis.service.repository.topic.TpTopicAssignRepository;
import com.thesis.service.service.topic.TopicAssignExtendService;
import com.thesis.service.service.topic.TopicAssignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/topic/assign")
@RequiredArgsConstructor
public class TopicAssignController
    extends
    AbstractBaseController<TpTopicAssignTable, TpTopicAssignRepository, TopicAssignService> {

  private final TopicAssignExtendService topicAssignExtendService;

  @GetMapping("/search/teacher")
  public List<TpTopicAssignTable> findByTeacherCode(@RequestParam @NotBlank String code,
      @RequestParam(defaultValue = "semester") String sort,
      @RequestParam(defaultValue = "true") boolean descend) {
    return super.service.findByTeacherCode(code, sort, descend);
  }

  @GetMapping("/search/topic")
  public Object search(@RequestParam String value,
      @RequestParam(defaultValue = "semester") String sort,
      @RequestParam(defaultValue = "true") boolean descend) {
    return super.service.searchIlikeTopicName(value, sort, descend);
  }

  @Override
  @PostMapping("/paging/search")
  public Object search(@RequestBody @Valid SearchRequest requestBody) {
    return this.topicAssignExtendService.search(requestBody);
  }

  @GetMapping("search/topic/{topicId}")
  public Object search(@PathVariable long topicId) {
    return super.service.findByTopicIdOrderSemester(topicId);
  }

}
