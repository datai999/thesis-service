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
  (subject_department_id, degree_id, education_method_id, major_id, first_name, last_name, email)
VALUES
    (1, 1, 1, 1, 'Nguyễn Đức Anh', 'Tài', 'tai.nguyen.cse.datai@hcmut.edu.vn')
  , (null, null, null, null, 'Giáo', 'Vụ', 'giaovu@hcmut.edu.vn')
  , (1, 1, null, null, 'Trưởng bộ môn', 'Hệ thống thông tin', 'headHTTT@hcmut.edu.vn')
  , (2, 2, null, null, 'Head', 'Công nghệ phần mềm', 'headCNPM@hcmut.edu.vn')
  , (3, 3, null, null, 'Head', 'Hệ thống và mạng', 'headHTVM@hcmut.edu.vn')
  , (null, null, 1, 1, 'Nguyễn Văn', 'D', 'nguyenvand@hcmut.edu.vn')
  , (null, null, 1, 2, 'Nguyễn Thị', 'E', 'nguyenvane@hcmut.edu.vn')
  , (null, null, 2, 1, 'Nguyễn Văn', 'F', 'nguyenvanf@hcmut.edu.vn')
  , (null, null, 2, 2, 'Nguyễn Thị', 'G', 'nguyenvang@hcmut.edu.vn')
ON CONFLICT DO NOTHING;
UPDATE us_user SET code = id, type = 'TEACHER';
UPDATE us_user SET type = 'STUDENT' WHERE id > 5;
UPDATE us_user SET permissions = array_cat(permissions, ARRAY[type]::text[]);
UPDATE us_user SET permissions = '{"STUDENT", "TEACHER", "HEAD_SUBJECT_DEPARTMENT", "EDUCATION_STAFF", "ADMIN"}' WHERE id = 1;
UPDATE us_user SET permissions = '{"EDUCATION_STAFF"}' WHERE id = 2;
UPDATE us_user SET permissions = '{"TEACHER", "HEAD_SUBJECT_DEPARTMENT"}' WHERE id > 2 AND id < 6;

INSERT INTO tp_topic
  (semester_id, thesis, name, subject_department_id)
VALUES
    (2, FALSE, '{"vi":"Đề tài thử nghiệm 1", "en": "Topic test 1"}', 1)
  , (2, FALSE, '{"vi":"Đề tài thử nghiệm 2", "en": "Topic test 2"}', 2)
  , (2, FALSE, '{"vi":"Đề tài thử nghiệm 3", "en": "Topic test 3"}', 1)
  , (2, TRUE, '{"vi":"Luận văn thử nghiệm 1", "en": "Thesis test 1"}', 1)
  , (2, TRUE, '{"vi":"Luận văn thử nghiệm 2", "en": "Thesis test 2"}', 1)
  , (2, TRUE, '{"vi":"Luận văn thử nghiệm 3", "en": "Thesis test 3"}', 1)
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
  (parent_id, display_order, mark, min_score, max_score, description)
VALUES
    (null, null, null, null, null, null)
      , (1, 1, FALSE, 0, 100, 'I. PHẦN ĐÁNH GIÁ ĐIỂM CỦA SINH VIÊN THỰC HIỆN LUẬN VĂN TỐT NGHIỆP. Hướng dẫn đánh giá: cho mỗi Tiêu chí đánh giá, Thầy/ Cô cho điểm đánh giá ở cột Điểm đánh giá tương ứng với lựa chọn A, B, C, hoặc D nhằm phản ánh kết quả luận văn cũng như năng lực và thái độ của sinh viên ngay sau khi thực hiện luận văn tốt nghiệp')
        , (2, 1, FALSE, 0, 50, '(i) KẾT QUẢ LUẬN VĂN SO VỚI NHIỆM VỤ CỦA ĐỀ TÀI ĐẶT RA. Tối đa 50đ')
          , (3, 1, TRUE, 0, 50, 'Câu 1. Đánh giá về kết quả luận văn so với nhiệm vụ của đề tài đặt ra. Tối đa 50đ')
            , (4, 1, FALSE, 0, 10, 'A. Kết quả chỉ đáp ứng một phần nhỏ nhiệm vụ của đề tài với khối lượng công việc dưới 50%.(0->10đ)')
            , (4, 4, FALSE, 47, 50, 'D. Kết quả đáp ứng đầy đủ nhiệm vụ của đề tài đặt ra với khối lượng công việc trên 95%.(47->50)')
            , (4, 2, FALSE, 10, 35, 'B. Kết quả đáp ứng phần nhiệm vụ cơ bản của đề tài đặt ra với khối lượng công việc từ 50% đến 70%.(10->35)')
            , (4, 3, FALSE, 35, 47, 'C. Kết quả đáp ứng phần lớn nhiệm vụ chính của đề tài đặt ra với khối lượng công việc từ 70% đến 95%.(35->47)')
        , (2, 2, FALSE, 0, 20, '(ii). VIỆC THỰC HIỆN ĐÁNH GIÁ KẾT QUẢ LUẬN VĂN CỦA SINH VIÊN. Tối đa 20đ')
          , (9, 1, TRUE, 0, 5, 'Câu 2. Đánh giá việc nhận diện các lợi ích thực tế của giải pháp trong luận văn')
            , (10, 1, FALSE, 0, 1, 'A. A.Sinh viên không nêu được các lợi ích thực tế của giải pháp trong luận văn. (0Điểm1)')
            , (10, 2, FALSE, 1, 3, 'B. B.Sinh viên nêu được một vài lợi ích thực tế của giải pháp trong luận văn về một vài khía cạnh nhất định nhưng không có minh chứng cụ thể. (1Điểm3)')
            , (10, 3, FALSE, 3, 4, 'C. C.Sinh viên nêu được các lợi ích thực tế của giải pháp trong luận văn về một vài khía cạnh nhất định và có kèm theo minh chứng cụ thể. (3Điểm4)')
            , (10, 4, FALSE, 4, 5, 'D. Sinh viên nêu được các lợi ích thực tế của giải pháp trong luận văn về các khía cạnh một cách toàn diện và có kèm theo đầy đủ các minh chứng cụ thể. (4Điểm5)')
          , (9, 2, TRUE, 0, 5, 'Câu 3. Đánh giá về những giải pháp được đề xuất trong luận văn để giải quyết vấn đề')
            , (15, 1, FALSE, 0, 1, 'A.Sinh viên nêu ra một giải pháp nhưng không biết ưu và nhược điểm. (0Điểm1)')
            , (15, 2, FALSE, 1, 3, 'B.Sinh viên nêu ra một giải pháp, và phân tích ưu và nhược điểm. (1Điểm3)')
            , (15, 3, FALSE, 3, 4, 'C.Sinh viên đưa ra nhiều giải pháp để giải quyết bài toán, có biện luận lựa chọn giải pháp nhưng chưa hợp lý. (3Điểm4)')
            , (15, 4, FALSE, 4, 5, 'D.Sinh viên đưa ra nhiều giải pháp để giải quyết bài toán, đồng thời nêu rõ lý do và biện luận đúng, rõ ràng tại sao lựa chọn giải pháp đó. (4Điểm5)')
          , (9, 3, TRUE, 0, 10, 'Câu 4. Đánh giá sản phẩm đạt được (mô hình, chương trình, hệ thống, …)')
            , (20, 1, FALSE, 0, 1, 'A.Sinh viên không thực hiện đánh giá sản phẩm đạt được của đề tài. (0Điểm1))')
            , (20, 2, FALSE, 1, 5, 'B.Sinh viên trình bày phần đánh giá cho đề tài nhưng chưa đánh giá cho sản phẩm đạt được của đề tài. (1Điểm5)')
            , (20, 3, FALSE, 5, 9, 'C.Sinh viên đánh giá sản phẩm đạt được của đề tài, ví dụ: bằng kỹ thuật kiểm tra chuyên môn hoặc làm thí nghiệm, nhưng không phù hợp với các yêu cầu của đề tài. (5Điểm9)')
            , (20, 4, FALSE, 9, 10, 'D.Sinh viên đánh giá sản phẩm đạt được của đề tài, ví dụ: bằng kỹ thuật kiểm tra chuyên môn hoặc làm thí nghiệm, phù hợp với các yêu cầu của đề tài. (9Điểm10)')
ON CONFLICT DO NOTHING;

INSERT INTO sc_template
  (root_criterion_id, name, description)
VALUES
    (1, 'Phieu Danh Gia LVTN Nganh CS _GVHD_20210622', 'Phiếu đánh giá LVTN gồm 2 phần: phần Đánh giá điểm của sinh viên thực hiện luận văn tốt nghiệp và phần Đánh giá dành cho chuẩn đầu ra của chương trình. Thầy/ Cô vui lòng đánh giá cho cả 2 phần')
  , (4, 'Phieu Danh Gia LVTN Nganh CS GVPB', 'Phiếu đánh giá LVTN gồm 2 phần: phần Đánh giá điểm của sinh viên thực hiện luận văn tốt nghiệp và phần Đánh giá dành cho chuẩn đầu ra của chương trình. Thầy/ Cô vui lòng đánh giá cho cả 2 phần')
  , (5, 'Phieu Danh Gia LVTN Nganh CS GVPB', 'Phiếu đánh giá LVTN gồm 2 phần: phần Đánh giá điểm của sinh viên thực hiện luận văn tốt nghiệp và phần Đánh giá dành cho chuẩn đầu ra của chương trình. Thầy/ Cô vui lòng đánh giá cho cả 2 phần')
ON CONFLICT DO NOTHING;

INSERT INTO sc_setting_template
  (major_id, thesis, topic_role, council_role_id, template_id)
VALUES
    (1, FALSE, 'GUIDE_TEACHER', null, 1)
  , (1, FALSE, 'REVIEW_TEACHER', null, 2)
  , (1, TRUE, 'GUIDE_TEACHER', null, 1)
  , (1, TRUE, null, 2, 3)
ON CONFLICT DO NOTHING;

INSERT INTO sc_score
  (topic_id, setting_template_id, teacher_id, student_id, criterion_id, score, comment)
VALUES
    (4, 3, 1, 1, 4, '30', 'Đạt 30 điểm')
  , (4, 3, 1, 1, 10, '5', 'Đạt tối đa')
ON CONFLICT DO NOTHING;

INSERT INTO tp_council
  (semester_id, subject_department_id)
VALUES
    (2, 1)
ON CONFLICT DO NOTHING;

INSERT INTO tp_council_member
  (council_id, role_id, member_id)
VALUES
    (1, 1, 2)
  , (1, 2, 1)
  , (1, 3, 4)
  , (1, 3, 3)
ON CONFLICT DO NOTHING;

UPDATE tp_topic SET council_id = 1 WHERE id = 4;