package com.thesis.service.service.topic.testsuite;

import java.util.Set;
import java.util.function.Supplier;

public interface CouncilServiceTS {

  Long VALID_TOPIC_ID = 1L;
  Set<Long> VALID_TOPIC_IDS = Set.of(VALID_TOPIC_ID);

  Supplier<Long> VALID_ID = () -> 1L;

}
