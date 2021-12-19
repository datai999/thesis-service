package com.thesis.service.service.score;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import java.util.ArrayList;
import java.util.List;
import com.thesis.service.model.score.CriterionTable;
import com.thesis.service.repository.score.CriterionRepository;
import com.thesis.service.service.AEntityServiceTest;
import org.junit.jupiter.api.Test;

public class CriterionServiceTest
    extends AEntityServiceTest<CriterionTable, CriterionRepository, CriterionService> {

  @Override
  protected CriterionService spyService() {
    super.entity.setChildren(new ArrayList<>(List.of(new CriterionTable())));
    return spy(new CriterionService());
  }

  @Test
  void recursiveSave_nullInput() {
    var actual = super.service.recursiveSave(null);
    assertNull(actual);
  }

  @Test
  void recursiveSave_nullId() {
    var input = new CriterionTable();
    var actual = super.service.recursiveSave(input);
    assertEquals(input, actual);
  }

  @Test
  void recursiveSave_haveChildren() {
    var input = new CriterionTable().setChildren(new ArrayList<>(List.of(new CriterionTable())));
    var actual = super.service.recursiveSave(input);
    assertEquals(input, actual);
  }


}
