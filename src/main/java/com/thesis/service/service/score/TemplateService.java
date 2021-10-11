package com.thesis.service.service.score;

import javax.transaction.Transactional;
import com.thesis.service.dto.score.response.TemplateResponse;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.repository.score.TemplateRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TemplateService
    extends ABaseService<TemplateTable, TemplateRepository> {

  private final CriterionService criterionService;

  @Override
  protected Class<?> getResponseClass() {
    return TemplateResponse.class;
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

  // @Override
  // protected Function<TemplateTable, ?> mapping() {
  // return entity -> {
  // var name = (Objects.nonNull(entity.getCouncilRole()))
  // ? entity.getCouncilRole().getName()
  // : messageSourceService.getMessage(
  // TopicRole.GUIDE_TEACHER.equals(entity.getTopicRole())
  // ? MessageCode.GUIDE_TEACHER
  // : MessageCode.REVIEW_TEACHER);
  // // return new CriterionRoleResponse(entity, name, entity.getTemplate());
  // return super.mapper.map(entity, TemplateResponse.class);
  // };
  // }

}
