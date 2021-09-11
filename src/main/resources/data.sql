INSERT INTO sy_major (id, name) VALUES (1, 'Khoa học máy tính') ON CONFLICT DO NOTHING;
INSERT INTO sy_major (id, name) VALUES (2, 'Kỹ thuật máy tính') ON CONFLICT DO NOTHING;

INSERT INTO sy_subject_department (id, name) VALUES (1, 'Hệ thống thông tin') ON CONFLICT DO NOTHING;
INSERT INTO sy_subject_department (id, name) VALUES (2, 'Công nghệ phần mềm') ON CONFLICT DO NOTHING;
INSERT INTO sy_subject_department (id, name) VALUES (3, 'Hệ thống và mạng') ON CONFLICT DO NOTHING;
INSERT INTO sy_subject_department (id, name) VALUES (4, 'Khoa học máy tính') ON CONFLICT DO NOTHING;
INSERT INTO sy_subject_department (id, name) VALUES (5, 'Kỹ thuật máy tính') ON CONFLICT DO NOTHING;

INSERT INTO sy_degree (id, name) VALUES (1, 'Thạc sĩ') ON CONFLICT DO NOTHING;
INSERT INTO sy_degree (id, name) VALUES (2, 'Tiến sĩ') ON CONFLICT DO NOTHING;
INSERT INTO sy_degree (id, name) VALUES (3, 'Giáo sư') ON CONFLICT DO NOTHING;

INSERT INTO sy_education_method (id, name) VALUES (1, 'Chính quy') ON CONFLICT DO NOTHING;
INSERT INTO sy_education_method (id, name) VALUES (2, 'Chất lượng cao') ON CONFLICT DO NOTHING;

-- data test

INSERT INTO us_user (code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
  VALUES ('12', 'Nguyễn Văn', 'A', 'nguyenvana@hcmut.edu.vn', 1, 1, 1, 1) ON CONFLICT DO NOTHING;
INSERT INTO us_user (code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
  VALUES ('25', 'Nguyễn Văn', 'B', 'nguyenvanb@hcmut.edu.vn', 1, 1, 1, 2) ON CONFLICT DO NOTHING;
INSERT INTO us_user (code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
  VALUES ('63', 'Nguyễn Thị', 'C', 'nguyenthic@hcmut.edu.vn', 1, 1, 1, 3) ON CONFLICT DO NOTHING;
INSERT INTO us_user (code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
  VALUES ('47', 'Nguyễn Văn', 'D', 'nguyenvand@hcmut.edu.vn', 1, 1, 2, 1) ON CONFLICT DO NOTHING;
INSERT INTO us_user (code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
  VALUES ('85', 'Nguyễn Văn', 'E', 'nguyenvane@hcmut.edu.vn', 1, 2, 3, 2) ON CONFLICT DO NOTHING;
INSERT INTO us_user (code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
  VALUES ('36', 'Nguyễn Thị', 'F', 'nguyenthif@hcmut.edu.vn', 2, 2, 4, 3) ON CONFLICT DO NOTHING;
INSERT INTO us_user (code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
  VALUES ('27', 'Nguyễn Thị', 'G', 'nguyenthig@hcmut.edu.vn', 2, 1, 5, 1) ON CONFLICT DO NOTHING;
INSERT INTO us_user (code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
  VALUES ('8', 'Nguyễn Thị', 'F', 'nguyenthih@hcmut.edu.vn', 1, 1, 1, 2) ON CONFLICT DO NOTHING;
INSERT INTO us_user (code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
  VALUES ('84', 'Nguyễn Thị', 'B', 'nguyenthib@hcmut.edu.vn', 2, 2, 3, 1) ON CONFLICT DO NOTHING;
INSERT INTO us_user (code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
  VALUES ('38', 'Nguyễn Thị', 'H', 'nguyenthih@hcmut.edu.vn', 2, 1, 5, 2) ON CONFLICT DO NOTHING;