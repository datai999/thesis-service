package com.thesis.service.constant;

public interface MessageCode {

  String GUIDE_TEACHER = "guideTeacher";
  String REVIEW_TEACHER = "reviewTeacher";

  interface Semester {
    String OVERDUE_TOPIC_REGISTER = "semester.overdueTopicRegister";
    String OVERDUE_TOPIC_CANCEL = "semester.overdueTopicCancel";
  }

  interface Topic {
    String FULL_MEMBER = "topic.fullMember";
    String EXIST_STUDENT = "topic.existStudent";
  }

  interface Student {
    String REGISTER_TOPIC = "student.registerTopic";
    String CANCEL_TOPIC = "student.cancelTopic";
  }

  interface Council {
    String COUNCIL = "council";
    String CODE = "council.code";
    String MEMBER_JOIN = "council.member.join";
    String REMOVE_ASSIGN = "council.removeAssign";
    String ASSIGNED = "council.assigned";
  }

  interface Error {
    String BLANK_TOKEN = "error.token.blank";
  }

}
