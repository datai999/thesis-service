package com.thesis.service.controller.br;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.br.ConstDataTable;
import com.thesis.service.service.br.ConstDataService;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/const")
@RequiredArgsConstructor
public class ConstDataController extends ABaseController<ConstDataTable, ConstDataService> {

  @Override
  @PostMapping
  public <D extends ConstDataTable> Object save(@RequestBody D requestBody) {
    var exists = super.service.findAll(Example.of(requestBody));
    return exists.isEmpty() ? super.save(requestBody) : exists.get(0);
  }

  @Override
  @PostMapping("/all")
  public <D extends ConstDataTable> Object saveAll(@RequestBody List<D> requestBody) {
    return Set.copyOf(requestBody).parallelStream().map(x -> {
      return super.service.save(x);
    }).collect(Collectors.toSet());
  }

  @GetMapping("types")
  public Object findAllType() {
    var mapResponse =
        super.findAll().parallelStream().collect(Collectors.groupingBy(ConstDataTable::getType));
    mapResponse.keySet().parallelStream().forEach(key -> {
      var mapSortResponse =
          mapResponse.get(key).parallelStream().sorted((x, y) -> x.getNo().compareTo(y.getNo()))
              .collect(Collectors.toList());
      mapResponse.put(key, mapSortResponse);
    });
    return mapResponse;
  }

  @GetMapping("types/value")
  public Object findAllTypeValue() {
    return super.service.findAllType();
  }

}
