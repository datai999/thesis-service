package com.thesis.service.service.br;

import java.util.List;
import com.thesis.service.dto.TypeValueDto;
import com.thesis.service.model.br.ConstDataTable;
import com.thesis.service.repository.br.ConstDataRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class ConstDataService extends ABaseService<ConstDataTable, ConstDataRepository>
    implements ConstDataRepository {

  @Override
  protected void preBuild(ConstDataTable entity) {
    // do nothing
  }

  @Override
  public List<TypeValueDto> findAllType() {
    return super.mainRepository.findAllType();
  }

}
