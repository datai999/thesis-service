package com.thesis.service.service.br;

import java.util.List;
import com.thesis.service.dto.TypeValueDto;
import com.thesis.service.model.br.BrConstDataTable;
import com.thesis.service.repository.br.BrConstDataRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class ConstDataService extends ABaseService<BrConstDataTable, BrConstDataRepository>
    implements BrConstDataRepository {

  @Override
  protected void preBuild(BrConstDataTable entity) {
    // do nothing
  }

  @Override
  public List<TypeValueDto> findAllType() {
    return super.mainRepository.findAllType();
  }

}
