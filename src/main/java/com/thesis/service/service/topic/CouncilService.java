package com.thesis.service.service.topic;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.dto.topic.resposne.CouncilResponse;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.topic.CouncilMemberRepository;
import com.thesis.service.repository.topic.CouncilRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.user.NotificationService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouncilService extends ABaseService<CouncilTable, CouncilRepository> {

  private final CouncilMemberRepository councilMemberRepository;
  private final TopicRepository topicRepository;
  private final NotificationService notificationService;

  @Override
  protected Function<CouncilTable, ?> mapping() {
    return CouncilResponse::new;
  }

  @Override
  public Object create(CouncilTable entity) {
    var council = this.repository.save(entity);
    var councilMembers = council.getMembers()
        .parallelStream().map(e -> e.setCouncil(council)).collect(Collectors.toList());
    councilMemberRepository.saveAll(councilMembers);

    var councilMembersGroupRole = councilMembers.parallelStream()
        .collect(Collectors.groupingBy(councilMember -> councilMember.getRole().getName()));

    var councilMessage = super.messageSource.toATag(council);
    councilMembersGroupRole.entrySet().forEach(
        entry -> {
          var roleMessage =
              super.messageSource.getMessage(
                  MessageCode.Council.MEMBER_JOIN,
                  entry.getKey(),
                  councilMessage);
          var receivers = entry.getValue().parallelStream()
              .map(CouncilMemberTable::getMember).collect(Collectors.toList());
          notificationService.notify(receivers, roleMessage);
        });
    return super.map(council);
  }

  @Override
  @Transactional
  public Object update(CouncilTable entity) {
    var existEntity = this.repository.findById(entity.getId()).orElseThrow();
    entity.setCreatedAt(existEntity.getCreatedAt());
    var council = this.repository.save(entity);

    var receivers = council.getMembers().parallelStream()
        .map(CouncilMemberTable::getMember).collect(Collectors.toList());
    receivers.addAll(entity.getMembers().parallelStream()
        .map(CouncilMemberTable::getMember).collect(Collectors.toList()));

    var councilMessage = super.messageSource.toATag(council);
    var message = super.messageSource.requestUserUpdate(councilMessage);
    this.notificationService.notify(receivers, message);

    this.councilMemberRepository.removeAllMember(council.getId());
    var councilMembers = entity.getMembers()
        .parallelStream().map(e -> e.setCouncil(council)).collect(Collectors.toList());
    councilMemberRepository.saveAll(councilMembers);

    return this.map(council);
  }

  @Transactional
  public Object assignTopic(long councilId, Set<Long> topicIds) {
    var council = super.repository.findById(councilId).orElseThrow();

    List<TopicTable> removeAssignTopics = council.getTopics().parallelStream()
        .filter(topic -> !topicIds.contains(topic.getId())).collect(Collectors.toList());
    var removeAssignTopicIds = removeAssignTopics.parallelStream()
        .map(TopicTable::getId).collect(Collectors.toSet());
    topicRepository.updateCouncil(null, removeAssignTopicIds);
    notificationService.notifyTopics(removeAssignTopics,
        super.messageSource.getMessage(MessageCode.Council.REMOVE_ASSIGN));

    council.getTopics().removeAll(removeAssignTopics);
    topicIds.removeAll(council.getTopics().parallelStream()
        .map(TopicTable::getId).collect(Collectors.toList()));
    topicRepository.updateCouncil(council, topicIds);
    notificationService.notifyTopicIds(topicIds,
        super.messageSource.getMessage(MessageCode.Council.ASSIGNED));

    return true;
  }

}
