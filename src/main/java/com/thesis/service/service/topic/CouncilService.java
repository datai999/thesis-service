package com.thesis.service.service.topic;

import java.util.stream.Collectors;
import com.thesis.service.dto.topic.resposne.CouncilResponse;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.repository.topic.CouncilMemberRepository;
import com.thesis.service.repository.topic.CouncilRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouncilService extends ABaseService<CouncilTable, CouncilRepository> {

  private final CouncilMemberRepository councilMemberRepository;

  @Override
  protected Class<?> getResponseClass() {
    return CouncilResponse.class;
  }

  @Override
  public Object create(CouncilTable entity) {
    var council = this.repository.save(entity);
    var councilMember = council.getMembers()
        .parallelStream().map(e -> e.setCouncil(council)).collect(Collectors.toList());
    councilMemberRepository.saveAll(councilMember);
    return council.getId();
  }

}
