package com.thesis.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.params.provider.Arguments;

public interface TestSuiteUtils {

  /**
   * Create list testcase:
   *
   * @param input [[true,false],[1,2,3]]
   * @return [[true, 1], [true,2], [true,3], [false,1], [false,2], [false,3]]
   */
  static List<Arguments> create(List<?>... input) {
    return (input.length < 2)
        ? input[0].stream().map(Arguments::of).collect(Collectors.toList())
        : input[0].stream()
            .map(argValue -> {
              var recursive = TestSuiteUtils.create(Arrays.copyOfRange(input, 1, input.length));
              return recursive.stream()
                  .map(x -> {
                    var args = new ArrayList<>();
                    args.add(argValue);
                    args.addAll(Arrays.asList(x.get()));
                    return Arguments.of(args.toArray());
                  })
                  .collect(Collectors.toList());
            })
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
  }

}
