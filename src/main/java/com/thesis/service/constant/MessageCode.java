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
    String NAME_NULL = "semester.name.null";
    String REGISTER_TOPIC_START_NULL = "semester.registerTopicStart.null";
    String REGISTER_TOPIC_END_NULL = "semester.registerTopicEnd.null";
  }

  interface Topic {
    String FULL_MEMBER = "topic.fullMember";
    String EXIST_STUDENT = "topic.existStudent";
    String LIST_VIEW = "topic.listView";
    String YOUR = "topic.your";
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

  interface User {
    String EMAIL_NOT_FOUND = "email.notFound";
  }

  interface My {
    String TOPIC_VIEW = "my.topic.view";
  }

  interface Timer {
    String REGISTER_TOPIC_END = "timer.registerTopicEnd";
    String TIMELINE_VIEW = "timer.timeline.view";
  }

  interface Mark {
    String MID_PASS = "mark.mid.pass";
    String MID_FAIL = "mark.mid.fail";
    String FINAL = "mark.final";
  }

  interface Assign {
    String REVIEW_ACTION = "assign.review.action";
    String YOU_REVIEW = "assign.you.review";
  }

}
