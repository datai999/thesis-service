package com.thesis.service.br.service;

import com.thesis.service.br.model.BrSettingTable;
import com.thesis.service.br.repository.BrSettingRepository;
import com.thesis.service.common.service.ABaseService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettingService extends ABaseService<BrSettingTable, BrSettingRepository> implements BrSettingRepository {

  @Override
  protected void preBuild(BrSettingTable entity) {
    // do nothing
  }

  @Override
  public BrSettingTable findByType(String type) {
    return super.mainRepository.findByType(type);
  }

}
