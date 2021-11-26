package com.thesis.service.service.score;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.thesis.service.dto.score.response.TemplateResponse;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.model.topic.CouncilRoleTable;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.score.TemplateRepository;
import com.thesis.service.repository.topic.CouncilMemberRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TemplateService
    extends ABaseService<TemplateTable, TemplateRepository> {

  private final CouncilMemberRepository councilMemberRepository;
  private final CriterionService criterionService;

  @Override
  protected Function<TemplateTable, ?> mapping() {
    return TemplateResponse::new;
  }

  @Override
  @Transactional
  public Object create(TemplateTable entity) {
    var rootCriterion = criterionService.recursiveSave(entity.getRootCriterion());
    var entityCreated = super.repository.save(entity.setRootCriterion(rootCriterion));
    return super.map(entityCreated);
  }

  @Override
  @Transactional
  public Object update(TemplateTable entity) {
    this.repository.findById(entity.getId()).orElseThrow();
    var rootCriterion = criterionService.recursiveSave(entity.getRootCriterion());
    var entityCreated = super.repository.save(entity.setRootCriterion(rootCriterion));
    return super.map(entityCreated);
  }

  @Override
  public Object findById(long id) {
    var response = this.repository.findById(id).orElseThrow();
    criterionService.sortChildren(response.getRootCriterion());
    return this.map(response);
  }

  public List<TemplateTable> shareFindByExample(TemplateTable entity, Sort sort) {
    var response = this.repository.findAll(Example.of(entity), sort);
    if (CollectionUtils.isNotEmpty(entity.getCouncilRoles())
        && entity.getCouncilRoles().size() > 0) {
      var roleIds = entity.getCouncilRoles().stream()
          .map(CouncilRoleTable::getId).collect(Collectors.toList());
      response = response.parallelStream()
          .filter(e -> e.getCouncilRoles().stream()
              .anyMatch(role -> roleIds.contains(role.getId())))
          .collect(Collectors.toList());
    }

    if (CollectionUtils.isNotEmpty(entity.getMajors())
        && entity.getMajors().size() > 0) {
      var majorIds = entity.getMajors().stream()
          .map(MajorTable::getId).collect(Collectors.toList());
      response = response.parallelStream()
          .filter(e -> e.getMajors().stream()
              .anyMatch(major -> majorIds.contains(major.getId())))
          .collect(Collectors.toList());
    }
    return response;
  }

  @Override
  public Object findByExample(TemplateTable entity, Sort sort) {
    var response = this.shareFindByExample(entity, sort);
    return this.map(response);
  }

  public Object getByCouncilMember(
      Sort sort, TemplateTable entity, long councilId, long teacherId) {
    var council = new CouncilTable();
    council.setId(councilId);
    var teacher = new UserTable();
    teacher.setId(teacherId);

    var councilMembers = councilMemberRepository.findAll(
        Example.of(new CouncilMemberTable().setCouncil(council).setMember(teacher)));
    entity.setCouncilRoles(
        councilMembers.stream().map(CouncilMemberTable::getRole).collect(Collectors.toList()));

    var response = this.shareFindByExample(entity, sort);
    return this.map(response);
  }

}
