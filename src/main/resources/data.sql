-- DROP CONTRAINT

ALTER TABLE tp_topic_assign ALTER COLUMN student_id DROP NOT NULL,
                            ALTER COLUMN guide_teacher_id DROP NOT NULL;

-- INIT DATA

INSERT INTO sy_major (id, name) VALUES
    (1, 'Khoa học máy tính')
  , (2, 'Kỹ thuật máy tính') 
ON CONFLICT DO NOTHING;

INSERT INTO sy_subject_department (id, name) VALUES 
    (1, 'Hệ thống thông tin')
  , (2, 'Công nghệ phần mềm')
  , (3, 'Hệ thống và mạng')
  , (4, 'Khoa học máy tính')
  , (5, 'Kỹ thuật máy tính') 
ON CONFLICT DO NOTHING;

INSERT INTO sy_degree (id, name) VALUES
    (1, 'Thạc sĩ')
  , (2, 'Tiến sĩ')
  , (3, 'Giáo sư') 
ON CONFLICT DO NOTHING;

INSERT INTO sy_education_method (id, name) VALUES 
    (1, 'Chính quy')
  , (2, 'Chất lượng cao')
ON CONFLICT DO NOTHING;

-- data test

INSERT INTO us_user
  (type, code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
VALUES 
    ('TEACHER', '12', 'Nguyễn Văn', 'A', 'nguyenvana@hcmut.edu.vn', 1, 1, 1, 1)
  , ('TEACHER', '25', 'Nguyễn Văn', 'B', 'nguyenvanb@hcmut.edu.vn', 1, 1, 1, 2)
  , ('TEACHER', '63', 'Nguyễn Thị', 'C', 'nguyenthic@hcmut.edu.vn', 1, 1, 1, 3)
  , ('TEACHER', '47', 'Nguyễn Văn', 'D', 'nguyenvand@hcmut.edu.vn', 1, 1, 2, 1)
  , ('TEACHER', '85', 'Nguyễn Văn', 'E', 'nguyenvane@hcmut.edu.vn', 1, 2, 3, 2)
  , ('TEACHER', '36', 'Nguyễn Thị', 'F', 'nguyenthif@hcmut.edu.vn', 2, 2, 4, 3)
  , ('TEACHER', '27', 'Nguyễn Thị', 'G', 'nguyenthig@hcmut.edu.vn', 2, 1, 5, 1)
  , ('STUDENT', '8', 'Nguyễn Thị', 'F', 'nguyenthih@hcmut.edu.vn', 1, 1, 1, 2)
  , ('STUDENT', '84', 'Nguyễn Thị', 'B', 'nguyenthib@hcmut.edu.vn', 2, 2, 3, 1)
  , ('STUDENT', '38', 'Nguyễn Thị', 'H', 'nguyenthih@hcmut.edu.vn', 2, 1, 5, 2)
ON CONFLICT DO NOTHING;