INSERT INTO sy_major (name) VALUES ('{"en":"Computer Science","vi":"Khoa học máy tính"}');
INSERT INTO sy_major (name) VALUES ('{"en":"Computer Engineering","vi":"Kỹ thuật máy tính"}');

INSERT INTO sy_subject_department (name) VALUES ('{"en":"Information System","vi":"Hệ thống thông tin"}');
INSERT INTO sy_subject_department (name) VALUES ('{"en":"Software Technology", "vi":"Công nghệ phần mềm"}');
INSERT INTO sy_subject_department (name) VALUES ('{"en":"Systems and Networks", "vi":"Hệ thống và mạng"}');
INSERT INTO sy_subject_department (name) VALUES ('{"en":"Computer Science","vi":"Khoa học máy tính"}');
INSERT INTO sy_subject_department (name) VALUES ('{"en":"Computer Engineering","vi":"Kỹ thuật máy tính"}');

INSERT INTO sy_degree (name) VALUES ('{"en":"Master", "vi":"Thạc sĩ"}');
INSERT INTO sy_degree (name) VALUES ('{"en":"Doctor", "vi":"Tiến sĩ"}');
INSERT INTO sy_degree (name) VALUES ('{"en":"Professor","en":"Giáo sư"}');

INSERT INTO br_const_data (type, no, value) VALUES ('educationMethod', 0, '{"en":"Formal","vi":"Chính quy"}');
INSERT INTO br_const_data (type, no, value) VALUES ('educationMethod', 1, '{"en":"CLC","vi":"Chất lượng cao"}');

-- data test

INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('1', 'Nguyễn Văn', 'A', 'nguyenvana', 1);
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('2', 'Nguyễn Văn', 'B', 'nguyenvanb', 2);
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('3', 'Nguyễn Thị', 'C', 'nguyenthic', 3);
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('4', 'Nguyễn Văn', 'D', 'nguyenvand', 4);
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('5', 'Nguyễn Văn', 'E', 'nguyenvane', 5);
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('6', 'Nguyễn Thị', 'F', 'nguyenthif', 1);
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('7', 'Nguyễn Thị', 'G', 'nguyenthig', 2);
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('8', 'Nguyễn Thị', 'F', 'nguyenthih', 3);