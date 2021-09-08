package com.thesis.service.controller.person;

import javax.validation.Valid;
import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.dto.SearchRequest;
import com.thesis.service.dto.person.response.StudentFlatResponse;
import com.thesis.service.model.person.StudentTable;
import com.thesis.service.repository.person.StudentRepository;
import com.thesis.service.service.person.StudentService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController
    extends AbstractBaseController<StudentTable, StudentRepository, StudentService> {

  @GetMapping("/flat")
  public Object reduce() {
    Sort sortable = Sort.by(Direction.DESC, "id");
    return service.getRepository().findAll(sortable).stream()
        .map(StudentFlatResponse::from);
  }

  @Override
  @PostMapping("/paging/search")
  public Object search(@RequestBody @Valid SearchRequest requestBody) {
    return super.service.search(requestBody);
  }
}
