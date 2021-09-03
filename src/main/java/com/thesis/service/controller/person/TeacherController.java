package com.thesis.service.controller.person;

import java.util.stream.Collectors;
import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.dto.person.response.TeacherFlatResponse;
import com.thesis.service.model.person.TeacherTable;
import com.thesis.service.repository.person.TeacherRepository;
import com.thesis.service.service.person.TeacherService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController
    extends AbstractBaseController<TeacherTable, TeacherRepository, TeacherService> {

  @GetMapping("/flat")
  public Object reduce() {
    Sort sortable = Sort.by(Direction.DESC, "id");
    return service.getRepository().findAll(sortable).stream()
        .map(TeacherFlatResponse::from).collect(Collectors.toList());
  }

  @GetMapping("search")
  public Object search(String value) {
    return super.service.getRepository().searchIlikeName(value);
  }

}
