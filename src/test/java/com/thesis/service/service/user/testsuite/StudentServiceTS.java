package com.thesis.service.service.user.testsuite;

import java.util.function.Supplier;
import java.util.stream.Stream;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.model.user.UserTable;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public interface StudentServiceTS {

  Long VALID_STUDENT_ID = 1L;
  Long INVALID_STUDENT_ID = 0L;
  Long VALID_TOPIC_ID = 1L;
  Long INVALID_TOPIC_ID = 0L;

  Supplier<UserTable> STUDENT = () -> new UserTable(VALID_STUDENT_ID);

  class UseMessageCode implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
        throws Exception {
      return Stream.of(
          Arguments.of(MessageCode.Semester.OVERDUE_TOPIC_REGISTER),
          Arguments.of(MessageCode.Topic.FULL_MEMBER),
          Arguments.of(MessageCode.Topic.EXIST_STUDENT),
          Arguments.of(MessageCode.Student.REGISTER_TOPIC),
          Arguments.of(MessageCode.Student.CANCEL_TOPIC));
    }
  }

  // class GroupNotFoundWhenCreateTeam implements ArgumentsProvider {
  // @Override
  // public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
  // throws Exception {
  // return Stream.of(
  // Arguments.of(MessageCode.Group.GROUP_NOT_FOUND,
  // VALID_GROUP_DTO.get().setType(TEAM_GROUP_TYPE), Optional.empty(), false),
  // Arguments.of(MessageCode.Group.GROUP_NOT_FOUND,
  // VALID_GROUP_DTO.get().setType(TEAM_GROUP_TYPE), Optional.of(VALID_ORGANIZATION.get()),
  // true));
  // }
  // }


}
