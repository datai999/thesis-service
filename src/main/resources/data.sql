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

INSERT INTO sy_semester (name, status, register_topic_start, register_topic_end) VALUES
    ('211', 'USED', '2021-01-17T00:12:00', '2021-03-17T00:12:00')
  , ('212', 'USING', '2021-06-17T00:12:00', '2021-11-17T00:12:00')
  , ('213', null, null, null)
ON CONFLICT DO NOTHING;

INSERT INTO tp_council_role (name, min, max, display_order) VALUES
    ('Chủ tịch', 1, 1, 1)
  , ('Thư ký', 1, 1, 2)
  , ('Ủy viên', 3, 3, 3)
ON CONFLICT DO NOTHING;

-- TEST DATA

INSERT INTO us_user
  (type, code, first_name, last_name, email, education_method_id, major_id, subject_department_id, degree_id)
VALUES
    ('TEACHER', '1713015', 'Nguyễn Đức Anh', 'Tài', 'tai.nguyen.cse.datai@hcmut.edu.vn', 1, 1, 1, 1)
  , ('TEACHER', '12', 'Nguyễn Văn', 'A', 'nguyenvana@hcmut.edu.vn', 1, 1, 1, 1)
  , ('TEACHER', '25', 'Nguyễn Văn', 'B', 'nguyenvanb@hcmut.edu.vn', 1, 1, 1, 2)
  , ('TEACHER', '63', 'Nguyễn Thị', 'C', 'nguyenthic@hcmut.edu.vn', 1, 1, 1, 3)
  , ('TEACHER', '47', 'Nguyễn Văn', 'D', 'nguyenvand@hcmut.edu.vn', 1, 1, 2, 1)
  , ('STUDENT', '85', 'Nguyễn Văn', 'E', 'nguyenvane@hcmut.edu.vn', 1, 2, 3, 2)
  , ('STUDENT', '36', 'Nguyễn Thị', 'F', 'nguyenthif@hcmut.edu.vn', 2, 2, 4, 3)
  , ('STUDENT', '27', 'Nguyễn Thị', 'G', 'nguyenthig@hcmut.edu.vn', 2, 1, 5, 1)
  , ('STUDENT', '8', 'Nguyễn Thị', 'F', 'nguyenthih@hcmut.edu.vn', 1, 1, 1, 2)
  , ('STUDENT', '84', 'Nguyễn Thị', 'B', 'nguyenthib@hcmut.edu.vn', 2, 2, 3, 1)
  , ('STUDENT', '38', 'Nguyễn Thị', 'H', 'nguyenthih@hcmut.edu.vn', 2, 1, 5, 2)
ON CONFLICT DO NOTHING;
UPDATE us_user SET roles = array_cat(roles, ARRAY[type]::text[]);
UPDATE us_user SET roles = array_append(roles, 'ADMIN') WHERE id = 1;

INSERT INTO tp_topic
  (semester_id, thesis, name, subject_department_id)
VALUES
    (2, false, '{"vi":"Đề tài thử nghiệm 1", "en": "Topic test 1"}', 1)
  , (2, false, '{"vi":"Đề tài thử nghiệm 2", "en": "Topic test 2"}', 2)
  , (2, false, '{"vi":"Đề tài thử nghiệm 3", "en": "Topic test 3"}', 1)
  , (2, true, '{"vi":"Luận văn thử nghiệm 1", "en": "Thesis test 1"}', 1)
  , (2, true, '{"vi":"Luận văn thử nghiệm 2", "en": "Thesis test 2"}', 1)
  , (2, true, '{"vi":"Luận văn thử nghiệm 3", "en": "Thesis test 3"}', 1)
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

INSERT INTO us_notification (receiver_id, message) VALUES
    (1, 'Tin nhắn thử nghiệm 2 <a href="http://localhost:3000/semesters">link</a>')
  , (1, 'Tin nhắn thử nghiệm 2 <a href="http://localhost:3000/semesters">link</a>')
  , (1, 'Tin nhắn thử nghiệm 2 <a href="http://localhost:3000/semesters">link</a>')
  , (1, 'Tin nhắn thử nghiệm 2 <a href="http://localhost:3000/semesters">link</a>')
  , (1, 'Tin nhắn thử nghiệm 2 <a href="http://localhost:3000/semesters">link</a>')
ON CONFLICT DO NOTHING;
INSERT INTO us_notification (receiver_id, message)
  SELECT id, 'Kết thúc thời gian đăng ký đề tài. <a target="_blank" href="http://localhost:3000/my/topics">chi tiết</a>'
  FROM us_user;

INSERT INTO sc_criterion
  (parent_id, display_order, min_score, max_score, formula, name, description)
VALUES
    (null, 1, null, null, null, 'Phiếu đánh giá dành cho GVHD', 'Phiếu đánh giá LVTN gồm 2 phần: phần Đánh giá điểm của sinh viên thực hiện luận văn tốt nghiệp và phần Đánh giá dành cho chuẩn đầu ra của chương trình. Thầy/ Cô vui lòng đánh giá cho cả 2 phần')
  , (1, 1, 0, 100, '#3', null, 'I. PHẦN ĐÁNH GIÁ ĐIỂM CỦA SINH VIÊN THỰC HIỆN LUẬN VĂN TỐT NGHIỆP. Hướng dẫn đánh giá: cho mỗi Tiêu chí đánh giá, Thầy/ Cô cho điểm đánh giá ở cột Điểm đánh giá tương ứng với lựa chọn A, B, C, hoặc D nhằm phản ánh kết quả luận văn cũng như năng lực và thái độ của sinh viên ngay sau khi thực hiện luận văn tốt nghiệp')
  , (2, 1, 0, 50, '#4', null, '(i) KẾT QUẢ LUẬN VĂN SO VỚI NHIỆM VỤ CỦA ĐỀ TÀI ĐẶT RA')
  , (3, 1, 0, 50, '#5+#6+#7+#8', null, 'Câu 1. Đánh giá về kết quả luận văn so với nhiệm vụ của đề tài đặt ra')
  , (4, 1, 0, 10, null, null, 'A. Kết quả chỉ đáp ứng một phần nhỏ nhiệm vụ của đề tài với khối lượng công việc dưới 50%.')
  , (4, 4, 47, 50, null, null, 'D. Kết quả đáp ứng đầy đủ nhiệm vụ của đề tài đặt ra với khối lượng công việc trên 95%')
  , (4, 2, 10, 35, null, null, 'B. Kết quả đáp ứng phần nhiệm vụ cơ bản của đề tài đặt ra với khối lượng công việc từ 50% đến 70%')
  , (4, 3, 35, 47, null, null, 'C. Kết quả đáp ứng phần lớn nhiệm vụ chính của đề tài đặt ra với khối lượng công việc từ 70% đến 95%')

ON CONFLICT DO NOTHING;