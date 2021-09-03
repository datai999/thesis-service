package com.thesis.service.repository.br;

import java.util.List;
import com.thesis.service.dto.TypeValueDto;
import com.thesis.service.model.br.BrConstDataTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrConstDataRepository extends BaseRepository<BrConstDataTable> {

  @Query(value = TypeValueDto.QUERY, nativeQuery = true)
  List<TypeValueDto> findAllType();

}
