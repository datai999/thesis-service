package com.thesis.service.service.topic.testsuite;

import java.time.LocalDate;
import java.time.Month;
import java.util.function.Supplier;

public interface CouncilMemberServiceTS {

  Supplier<LocalDate> RESERVED_DATE = () -> LocalDate.of(2021, Month.DECEMBER, 19);

}
