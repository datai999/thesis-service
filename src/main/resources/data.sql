INSERT INTO sy_major (id, name) VALUES (1, '{"en":"Computer Science","vi":"Khoa học máy tính"}') ON CONFLICT DO NOTHING;
INSERT INTO sy_major (id, name) VALUES (2, '{"en":"Computer Engineering","vi":"Kỹ thuật máy tính"}') ON CONFLICT DO NOTHING;

INSERT INTO sy_subject_department (id, name) VALUES (1, '{"en":"Information System","vi":"Hệ thống thông tin"}') ON CONFLICT DO NOTHING;
INSERT INTO sy_subject_department (id, name) VALUES (2, '{"en":"Software Technology", "vi":"Công nghệ phần mềm"}') ON CONFLICT DO NOTHING;
INSERT INTO sy_subject_department (id, name) VALUES (3, '{"en":"Systems and Networks", "vi":"Hệ thống và mạng"}') ON CONFLICT DO NOTHING;
INSERT INTO sy_subject_department (id, name) VALUES (4, '{"en":"Computer Science","vi":"Khoa học máy tính"}') ON CONFLICT DO NOTHING;
INSERT INTO sy_subject_department (id, name) VALUES (5, '{"en":"Computer Engineering","vi":"Kỹ thuật máy tính"}') ON CONFLICT DO NOTHING;

INSERT INTO sy_degree (id, name) VALUES (1, '{"en":"Master", "vi":"Thạc sĩ"}') ON CONFLICT DO NOTHING;
INSERT INTO sy_degree (id, name) VALUES (2, '{"en":"Doctor", "vi":"Tiến sĩ"}') ON CONFLICT DO NOTHING;
INSERT INTO sy_degree (id, name) VALUES (3, '{"en":"Professor","en":"Giáo sư"}') ON CONFLICT DO NOTHING;

INSERT INTO sy_education_method (id, name) VALUES (1, 'Chính quy') ON CONFLICT DO NOTHING;
INSERT INTO sy_education_method (id, name) VALUES (2, 'Chất lượng cao') ON CONFLICT DO NOTHING;

-- data test

INSERT INTO ps_person (code, first_name, last_name, email)
  VALUES ('12', 'Nguyễn Văn', 'A', 'nguyenvana') ON CONFLICT DO NOTHING;
INSERT INTO ps_person (code, first_name, last_name, email)
  VALUES ('25', 'Nguyễn Văn', 'B', 'nguyenvanb') ON CONFLICT DO NOTHING;
INSERT INTO ps_person (code, first_name, last_name, email)
  VALUES ('63', 'Nguyễn Thị', 'C', 'nguyenthic') ON CONFLICT DO NOTHING;
INSERT INTO ps_person (code, first_name, last_name, email)
  VALUES ('47', 'Nguyễn Văn', 'D', 'nguyenvand') ON CONFLICT DO NOTHING;
INSERT INTO ps_person (code, first_name, last_name, email)
  VALUES ('85', 'Nguyễn Văn', 'E', 'nguyenvane') ON CONFLICT DO NOTHING;
INSERT INTO ps_person (code, first_name, last_name, email)
  VALUES ('36', 'Nguyễn Thị', 'F', 'nguyenthif') ON CONFLICT DO NOTHING;
INSERT INTO ps_person (code, first_name, last_name, email)
  VALUES ('27', 'Nguyễn Thị', 'G', 'nguyenthig') ON CONFLICT DO NOTHING;
INSERT INTO ps_person (code, first_name, last_name, email)
  VALUES ('8', 'Nguyễn Thị', 'F', 'nguyenthih') ON CONFLICT DO NOTHING;
INSERT INTO ps_person (code, first_name, last_name, email)
  VALUES ('84', 'Nguyễn Thị', 'B', 'nguyenthib') ON CONFLICT DO NOTHING;
INSERT INTO ps_person (code, first_name, last_name, email)
  VALUES ('38', 'Nguyễn Thị', 'H', 'nguyenthih') ON CONFLICT DO NOTHING;

INSERT INTO ps_teacher (person_id, subject_department_id, degree_id) VALUES (1, 1, 1) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (person_id, subject_department_id, degree_id) VALUES (2, 2, 2) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (person_id, subject_department_id, degree_id) VALUES (3, 3, 3) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (person_id, subject_department_id, degree_id) VALUES (4, 4, 1) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (person_id, subject_department_id, degree_id) VALUES (5, 5, 2) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (person_id, subject_department_id, degree_id) VALUES (6, 1, 3) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (person_id, subject_department_id, degree_id) VALUES (7, 2, 1) ON CONFLICT DO NOTHING;

INSERT INTO ps_student (person_id, education_method_id, major_id) VALUES (8, 1, 1) ON CONFLICT DO NOTHING;
INSERT INTO ps_student (person_id, education_method_id, major_id) VALUES (9, 1, 2) ON CONFLICT DO NOTHING;
INSERT INTO ps_student (person_id, education_method_id, major_id) VALUES (10, 2, 1) ON CONFLICT DO NOTHING;