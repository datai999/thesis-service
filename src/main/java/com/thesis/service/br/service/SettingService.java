package com.thesis.service.br.service;

import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.br.model.BrSettingTable;
import com.thesis.service.br.repository.BrSettingRepository;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.service.ABaseService;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettingService extends ABaseService<BrSettingTable, BrSettingRepository> implements BrSettingRepository {

  @Override
  protected void preBuild(BrSettingTable entity) {
    // do nothing
  }

  public <T extends BaseTable> Object setting(String type, T refRecord) {
    var constDataExample = Example.of(BrConstDataTable.type(type));
    var constDataRecord = super.constRepository.findAll(constDataExample).stream().findFirst().orElseThrow();

    var settingExample = Example.of(BrSettingTable.name(constDataRecord));
    var settingRecord = super.mainRepository.findAll(settingExample).stream().findFirst().orElse(new BrSettingTable());
    settingRecord.setRef(refRecord).setName(constDataRecord);

    return super.save(settingRecord);
  }

}
