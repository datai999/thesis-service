package com.thesis.service.service.person;

import java.util.List;
import com.thesis.service.model.person.PersonBaseTable;
import com.thesis.service.service.IService;

public interface IPersonService<E extends PersonBaseTable> extends IService<E>{
  List<E> findAllByCode(Iterable<String> codes);
}
