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

INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('12', 'Nguyễn Văn', 'A', 'nguyenvana', 1) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('25', 'Nguyễn Văn', 'B', 'nguyenvanb', 2) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('63', 'Nguyễn Thị', 'C', 'nguyenthic', 3) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('47', 'Nguyễn Văn', 'D', 'nguyenvand', 4) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('85', 'Nguyễn Văn', 'E', 'nguyenvane', 5) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('36', 'Nguyễn Thị', 'F', 'nguyenthif', 1) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('27', 'Nguyễn Thị', 'G', 'nguyenthig', 2) ON CONFLICT DO NOTHING;
INSERT INTO ps_teacher (code, first_name, last_name, email, subject_department_id)
  VALUES ('8', 'Nguyễn Thị', 'F', 'nguyenthih', 3) ON CONFLICT DO NOTHING;

INSERT INTO ps_student (code, first_name, last_name, email, education_method_id, major_id) 
  VALUES ('1713015', 'Nguyễn Đức Anh', 'Tài', 'tai.nguyen.cse.datai', 1, 1) ON CONFLICT DO NOTHING;
INSERT INTO ps_student (code, first_name, last_name, email, education_method_id, major_id)
  VALUES ('1713016', 'Nguyễn Sinh Viên', '1', 'sv1', 2, 1) ON CONFLICT DO NOTHING;
INSERT INTO ps_student (code, first_name, last_name, email, education_method_id, major_id)
  VALUES ('1713032', 'Nguyễn Sinh Viên', '2', 'sv2', 1, 2) ON CONFLICT DO NOTHING;
INSERT INTO ps_student (code, first_name, last_name, email, education_method_id, major_id)
  VALUES ('1713043', 'Nguyễn Sinh', 'Viên', 'sv', 2, 2) ON CONFLICT DO NOTHING;
INSERT INTO ps_student (code, first_name, last_name, email, education_method_id, major_id)
  VALUES ('1713045', 'Nguyễn Sinh Viên', 'C', 'svc', 2, 1) ON CONFLICT DO NOTHING;
INSERT INTO ps_student (code, first_name, last_name, email, education_method_id, major_id)
  VALUES ('1713052', 'Nguyễn Sinh Văn', 'Viên', 'svv', 1, 1) ON CONFLICT DO NOTHING;