package com.thesis.service.service.br;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.br.ConstDataTable;
import com.thesis.service.model.br.SettingTable;
import com.thesis.service.repository.BaseRepository;
import com.thesis.service.repository.br.SettingRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.IService;
import com.thesis.service.service.score.CriterionTemplateService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettingService extends ABaseService<SettingTable, SettingRepository>
    implements SettingRepository {

  final CriterionTemplateService criterionTemplateService;

  @Override
  protected void preBuild(SettingTable entity) {

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
      S convertService, SettingTable entity) {
    var response = convertService.findAllById(entity.getRefId());
    return response.stream().map(item -> convertService.build(item)).collect(Collectors.toList());
  }

  public <T extends BaseTable> Object setting(String type, T refRecord) {
    var constDataExample = Example.of(ConstDataTable.type(type));
    var constDataRecord =
        super.constRepository.findAll(constDataExample).stream().findFirst().orElseThrow();

    var settingExample = Example.of(SettingTable.name(constDataRecord));
    var settingRecord = super.mainRepository.findAll(settingExample).stream().findFirst()
        .orElse(new SettingTable());
    settingRecord.setRef(refRecord).setName(constDataRecord);

    return super.save(settingRecord);
  }

  @Override
  public SettingTable findByType(String type) {
    return this.build(super.mainRepository.findByType(type));
  }

}
