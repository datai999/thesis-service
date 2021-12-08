package com.thesis.service.service.user.testsuite;

import com.thesis.service.constant.MessageCode;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface StudentServiceTS {

  // String VALID_GROUP_NAME = "validGroupName";
  // String VALID_GROUP_CODE = "validGroupCode";
  // EGroupType ORG_GROUP_TYPE = EGroupType.ORGANIZATION;
  // EGroupType TEAM_GROUP_TYPE = EGroupType.TEAM;
  // Long VALID_ID = 0L;

  // Supplier<GroupDto> VALID_GROUP_DTO = () -> new GroupDto()
  // .setName(VALID_GROUP_NAME)
  // .setCode(VALID_GROUP_CODE)
  // .setType(ORG_GROUP_TYPE);

  // Supplier<Organization> VALID_ORGANIZATION = () -> new Organization().setId(VALID_ID);


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
