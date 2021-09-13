// package com.thesis.service.controller.topic;

// import java.util.List;
// import javax.validation.constraints.NotBlank;
// import com.thesis.service.controller.ABaseController;
// import com.thesis.service.model.topic.TopicAssignTable;
// import com.thesis.service.service.topic.TopicAssignService;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import lombok.RequiredArgsConstructor;

// // @RestController
// @RequestMapping("/topic/assign")
// @RequiredArgsConstructor
// public class TopicAssignController
//     extends
//     ABaseController<TopicAssignTable, TopicAssignService> {

//   @GetMapping("/search/teacher")
//   public List<TopicAssignTable> findByTeacherCode(@RequestParam @NotBlank String code,
//       @RequestParam(defaultValue = "semester") String sort,
//       @RequestParam(defaultValue = "true") boolean descend) {
//     return super.service.findByTeacherCode(code, sort, descend);
//   }

//   @GetMapping("/search/topic")
//   public Object search(@RequestParam String value,
//       @RequestParam(defaultValue = "semester") String sort,
//       @RequestParam(defaultValue = "true") boolean descend) {
//     return super.service.searchIlikeTopicName(value, sort, descend);
//   }

//   @GetMapping("search/topic/{topicId}")
//   public Object search(@PathVariable long topicId) {
//     return super.service.findByTopicIdOrderSemester(topicId);
//   }

// }
