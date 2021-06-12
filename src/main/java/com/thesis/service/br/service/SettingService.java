package com.thesis.service.br.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.br.model.BrSettingTable;
import com.thesis.service.br.repository.BrSettingRepository;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.common.service.ABaseService;
import com.thesis.service.common.service.IService;
import com.thesis.service.score.service.CriterionTemplateService;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettingService extends ABaseService<BrSettingTable, BrSettingRepository> implements BrSettingRepository {

  final CriterionTemplateService criterionTemplateService;

  @Override
  protected void preBuild(BrSettingTable entity) {

    if (Objects.isNull(entity.getRefId())) {
      return;
    }

    List<? extends BaseTable> setting = null;

    switch (entity.getRefTable()) {
    case "sc_criterion_template":
      setting = buildService(criterionTemplateService, entity);
      break;
    default:
      break;
    }

    entity.setSetting(setting);
  }

  private <E extends BaseTable, S extends BaseRepository<E> & IService<E>> List<? extends BaseTable> buildService(
      S convertService, BrSettingTable entity) {
    var response = convertService.findAllById(entity.getRefId());
    return response.stream().map(item -> convertService.build(item)).collect(Collectors.toList());
  }

  public <T extends BaseTable> Object setting(String type, T refRecord) {
    var constDataExample = Example.of(BrConstDataTable.type(type));
    var constDataRecord = super.constRepository.findAll(constDataExample).stream().findFirst().orElseThrow();

    var settingExample = Example.of(BrSettingTable.name(constDataRecord));
    var settingRecord = super.mainRepository.findAll(settingExample).stream().findFirst().orElse(new BrSettingTable());
    settingRecord.setRef(refRecord).setName(constDataRecord);

    return super.save(settingRecord);
  }

  @Override
  public BrSettingTable findByType(String type) {
    return this.build(super.mainRepository.findByType(type));
  }

}
