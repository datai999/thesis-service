package com.thesis.service.repository.br;

import java.util.List;
import com.thesis.service.dto.TypeValueDto;
import com.thesis.service.model.br.ConstDataTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstDataRepository extends BaseRepository<ConstDataTable> {

  @Query(value = TypeValueDto.QUERY, nativeQuery = true)
  List<TypeValueDto> findAllType();

}
