package com.thesis.service.constant;

public interface MessageCode {

  String GUIDE_TEACHER = "guideTeacher";
  String GUIDE_TEACHER_MINI = "guideTeacher.mini";
  String REVIEW_TEACHER = "reviewTeacher";
  String REVIEW_TEACHER_MINI = "reviewTeacher.mini";
  String OUTLINE = "outline";
  String THESIS = "thesis";

  interface Semester {
    String OVERDUE_TOPIC_REGISTER = "semester.overdueTopicRegister";
    String OVERDUE_TOPIC_CANCEL = "semester.overdueTopicCancel";
    String MID = "semester.mid";
    String FINAL = "semester.final";
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
