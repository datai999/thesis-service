package com.thesis.service.service.topic;

import java.time.LocalDate;
import java.util.stream.Collectors;
import com.thesis.service.dto.topic.resposne.CouncilMemberResponse;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.repository.topic.CouncilMemberRepository;
import com.thesis.service.repository.topic.CouncilRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouncilMemberService
    extends ABaseService<CouncilMemberTable, CouncilMemberRepository> {

  private final CouncilRepository councilRepository;
  private final CouncilService councilService;

  @Override
  protected Class<?> getResponseClass() {
    return CouncilMemberResponse.class;
  }

  public Object getCouncil(CouncilMemberTable entity, Sort sort) {
    var queryResult = this.repository.findAll(Example.of(entity), sort);
    return councilService.map(queryResult.parallelStream()
        .map(CouncilMemberTable::getCouncil).collect(Collectors.toList()));
  }

  public Object checkConflictTimeLine(long councilId, LocalDate reserveDate) {
    var council = councilRepository.findById(councilId).orElseThrow();
    var memberIds = council.getMembers().stream()
        .map(councilMember -> councilMember.getMember().getId()).collect(Collectors.toList());
    var response = super.repository.getConflictTimeLine(memberIds, councilId, reserveDate);
    return super.map(response);
  }

}
