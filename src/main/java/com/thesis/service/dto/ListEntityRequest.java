package com.thesis.service.dto;

import java.util.List;
import com.thesis.service.model.BaseTable;
import lombok.Data;

@Data
public class ListEntityRequest<E extends BaseTable> {

  private List<E> entities = List.of();

}
