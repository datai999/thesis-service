package com.thesis.service.person.service;

import java.util.List;
import com.thesis.service.common.service.IService;
import com.thesis.service.model.person.PersonBaseTable;

public interface IPersonService<E extends PersonBaseTable> extends IService<E>{
  List<E> findAllByCode(Iterable<String> codes);
}
