package com.thesis.service.service.score;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import com.thesis.service.advice.BusinessException;
import com.thesis.service.dto.score.CriterionResponse;
import com.thesis.service.model.score.CriterionTable;
import com.thesis.service.repository.score.CriterionRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriterionService extends ABaseService<CriterionTable, CriterionRepository> {

  @Override
  protected Class<?> getResponseClass() {
    return CriterionResponse.class;
  }

  public void sortChildren(CriterionTable template) {
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
    if (Objects.nonNull(response.getParent())) {
      throw BusinessException.code("criterion-template.notFound", id);
    }
    this.sortChildren(response);
    return this.map(response);
  }

  private Collection<Long> getChildrenIds(CriterionTable entity) {
    return entity.getChildren().parallelStream()
        .map(CriterionTable::getId).collect(Collectors.toList());
  }

  @Transactional
  public CriterionTable recursiveSave(CriterionTable entity) {
    if (Objects.nonNull(entity.getId()))
      super.repository.findById(entity.getId())
          .ifPresent(createdEntity -> {
            var newChildrenIds = this.getChildrenIds(entity);
            var notUseIds = this.getChildrenIds(createdEntity);
            notUseIds.removeAll(newChildrenIds);
            notUseIds.parallelStream().distinct()
                .filter(Objects::nonNull)
                .forEach(super.repository::deleteById);
          });

    var savedEntity = this.repository.save(entity);
    for (int i = 0; i < entity.getChildren().size(); i++) {
      var child = entity.getChildren().get(i).setParent(savedEntity).setDisplayOrder(i);
      var savedChild = this.recursiveSave(child);
      savedEntity.getChildren().set(i, savedChild);
    }
    return savedEntity;
  }

  @Override
  public Object create(CriterionTable entity) {
    var response = this.recursiveSave(entity);
    this.sortChildren(response);
    return super.map(response);
  }

  @Override
  public Object update(CriterionTable entity) {
    var response = this.recursiveSave(entity);
    this.sortChildren(response);
    return super.map(response);
  }

}
