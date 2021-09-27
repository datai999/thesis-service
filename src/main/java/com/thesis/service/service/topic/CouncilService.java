package com.thesis.service.service.topic;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.thesis.service.dto.topic.resposne.CouncilResponse;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.topic.CouncilMemberRepository;
import com.thesis.service.repository.topic.CouncilRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouncilService extends ABaseService<CouncilTable, CouncilRepository> {

  private final CouncilMemberRepository councilMemberRepository;
  private final TopicRepository topicRepository;

  @Override
  protected Function<CouncilTable, ?> mapping() {
    return CouncilResponse::new;
  }

  @Override
  public Object create(CouncilTable entity) {
    var council = this.repository.save(entity);
    var councilMember = council.getMembers()
        .parallelStream().map(e -> e.setCouncil(council)).collect(Collectors.toList());
    councilMemberRepository.saveAll(councilMember);
    return council.getId();
  }

  @Override
  @Transactional
  public Object update(CouncilTable entity) {
    var council = this.repository.findById(entity.getId()).orElseThrow();
    this.councilMemberRepository.removeAllMember(council.getId());
    var councilMember = entity.getMembers()
        .parallelStream().map(e -> e.setCouncil(council)).collect(Collectors.toList());
    councilMemberRepository.saveAll(councilMember);
    return super.update(entity);
  }

  @Transactional
  public Object assignTopic(long councilId, Set<Long> topicIds) {
    var council = super.repository.findById(councilId).orElseThrow();

    List<TopicTable> removeAssignTopics = council.getTopics().parallelStream()
        .filter(topic -> !topicIds.contains(topic.getId())).collect(Collectors.toList());
    var removeAssignTopicIds = removeAssignTopics.parallelStream()
        .map(TopicTable::getId).collect(Collectors.toSet());
    topicRepository.updateCouncil(null, removeAssignTopicIds);

    council.getTopics().removeAll(removeAssignTopics);
    topicIds.removeAll(council.getTopics().parallelStream()
        .map(TopicTable::getId).collect(Collectors.toList()));
    topicRepository.updateCouncil(council, topicIds);

    return true;
  }

}
