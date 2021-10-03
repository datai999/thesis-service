package com.thesis.service.service.score;

import java.util.stream.Collectors;
import com.thesis.service.dto.score.CriterionResponse;
import com.thesis.service.dto.score.CriterionTemplateResponse;
import com.thesis.service.model.score.CriterionTable;
import com.thesis.service.repository.score.CriterionRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CriterionService extends ABaseService<CriterionTable, CriterionRepository> {

  @Override
  protected Class<?> getResponseClass() {
    return CriterionResponse.class;
  }

  public Object getTemplates(Sort sort) {
    var templates = super.repository.findByDeletedFalseAndParent(null, sort);
    return super.mapper.map(templates, CriterionTemplateResponse.class);
  }

  private void sortChildren(CriterionTable template) {
    if (CollectionUtils.isEmpty(template.getChildren()))
      return;
    var childrenSorted = template.getChildren().parallelStream()
        .sorted((x, y) -> Integer.compare(x.getDisplayOrder(), y.getDisplayOrder()))
        .collect(Collectors.toList());
    childrenSorted.parallelStream().forEach(this::sortChildren);
    template.setChildren(childrenSorted);
  }

  @Override
  public Object findById(long id) {
    var response = this.repository.findById(id).orElseThrow();
    this.sortChildren(response);
    return this.map(response);
  }

}
