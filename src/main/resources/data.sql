-- DROP CONTRAINT

ALTER TABLE tp_topic_assign ALTER COLUMN student_id DROP NOT NULL,
                            ALTER COLUMN guide_teacher_id DROP NOT NULL,
                            ALTER COLUMN review_teacher_id DROP NOT NULL;

ALTER TABLE tp_topic_property ALTER COLUMN major_id DROP NOT NULL,
                              ALTER COLUMN education_method_id DROP NOT NULL;

-- INIT DATA

INSERT INTO sy_education_method (name) VALUES ('Chính quy'), ('Chất lượng cao') ON CONFLICT DO NOTHING;
INSERT INTO sy_major (name) VALUES ('Khoa học máy tính'), ('Kỹ thuật máy tính') ON CONFLICT DO NOTHING;
INSERT INTO sy_degree (name) VALUES ('Thạc sĩ'), ('Tiến sĩ'), ('Giáo sư') ON CONFLICT DO NOTHING;

INSERT INTO sy_subject_department (name) VALUES
    ('Hệ thống thông tin'), ('Công nghệ phần mềm'), ('Hệ thống và mạng')
  , ('Khoa học máy tính'), ('Kỹ thuật máy tính')
ON CONFLICT DO NOTHING;

INSERT INTO sy_semester (name) VALUES ('210'), ('211'), ('212') ON CONFLICT DO NOTHING;

-- TEST DATA

INSERT INTO us_user
  (type, code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
VALUES
    ('TEACHER', '1713015', 'Nguyễn Đức Anh', 'Tài', 'tai.nguyen.cse.datai@hcmut.edu.vn', 1, 1, 1, 1)
  , ('TEACHER', '12', 'Nguyễn Văn', 'A', 'nguyenvana@hcmut.edu.vn', 1, 1, 1, 1)
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

INSERT INTO tp_topic
  (semester_id, thesis, name)
VALUES
    (1, false, '{"vi":"Đề tài thử nghiệm 1", "en": "Topic test 1"}')
  , (1, false, '{"vi":"Đề tài thử nghiệm 2", "en": "Topic test 2"}')
  , (1, false, '{"vi":"Đề tài thử nghiệm 3", "en": "Topic test 3"}')
  , (1, true, '{"vi":"Luận văn thử nghiệm 1", "en": "Thesis test 1"}')
  , (1, true, '{"vi":"Luận văn thử nghiệm 2", "en": "Thesis test 2"}')
  , (1, true, '{"vi":"Luận văn thử nghiệm 3", "en": "Thesis test 3"}')
ON CONFLICT DO NOTHING;

INSERT INTO tp_topic_assign
  (topic_id, student_id, guide_teacher_id, review_teacher_id)
VALUES
    (1, 1, 1, 1)
  , (2, null, 1, 1)
  , (3, null, 1, null)
  , (4, 1, 1, null)
  , (5, null, 1, null)
  , (6, null, 1, null)
ON CONFLICT DO NOTHING;

INSERT INTO tp_topic_property
  (topic_id, major_id, education_method_id)
VALUES
    (1, 1, 1)
  , (1, 2, 2)
ON CONFLICT DO NOTHING;