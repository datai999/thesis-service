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

INSERT INTO sy_semester
  (name, status, register_topic_start, register_topic_end, topic_start, topic_end, thesis_start, thesis_end)
VALUES
    ('201', 'USED', '2021-01-17T00:12:00', '2021-03-17T00:12:00', '2021-03-17T00:12:00', '2021-05-17T00:12:00', '2021-03-17T00:12:00', '2021-06-17T00:12:00')
  , ('211', 'USED', '2021-01-17T00:12:00', '2021-03-17T00:12:00', '2021-03-17T00:12:00', '2021-05-17T00:12:00', '2021-03-17T00:12:00', '2021-06-17T00:12:00')
  , ('212', 'USING', '2021-06-17T00:12:00', '2021-11-10T00:12:00', '2021-11-1T00:12:00', '2022-03-01T00:12:00', '2021-11-1T00:12:00', '2022-04-17T00:12:00')
  , ('213', null, null, null, null, null, null, null)
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
  , (1, 3, null, null, 'Nguyễn Văn', 'A', 'a@hcmut.edu.vn')
  , (1, 3, null, null, 'Nguyễn Văn', 'B', 'b@hcmut.edu.vn')
  , (1, 3, null, null, 'Nguyễn Văn', 'C', 'c@hcmut.edu.vn')
  , (null, null, 1, 1, 'Nguyễn Văn', 'D', 'nguyenvand@hcmut.edu.vn')
  , (null, null, 1, 2, 'Nguyễn Thị', 'E', 'nguyenvane@hcmut.edu.vn')
  , (null, null, 2, 1, 'Nguyễn Văn', 'F', 'nguyenvanf@hcmut.edu.vn')
  , (null, null, 2, 2, 'Nguyễn Thị', 'G', 'nguyenvang@hcmut.edu.vn')
ON CONFLICT DO NOTHING;
UPDATE us_user SET code = id;
UPDATE us_user SET type = 'TEACHER' WHERE id != 2;
UPDATE us_user SET type = 'STUDENT' WHERE id >= 9;
UPDATE us_user SET permissions = array_cat(permissions, ARRAY[type]::text[]);
UPDATE us_user SET permissions = '{"STUDENT", "TEACHER", "HEAD_SUBJECT_DEPARTMENT", "EDUCATION_STAFF", "ADMIN"}' WHERE id = 1;
UPDATE us_user SET permissions = '{"EDUCATION_STAFF"}' WHERE id = 2;
UPDATE us_user SET permissions = '{"TEACHER", "HEAD_SUBJECT_DEPARTMENT"}' WHERE id >= 3 AND id <= 8;

INSERT INTO tp_topic
  (semester_id, thesis, name, subject_department_id)
VALUES
    (3, FALSE, '{"vi":"Đề tài thử nghiệm 1", "en": "Topic test 1"}', 1)
  , (3, FALSE, '{"vi":"Đề tài thử nghiệm 2", "en": "Topic test 2"}', 2)
  , (3, FALSE, '{"vi":"Đề tài thử nghiệm 3", "en": "Topic test 3"}', 1)
  , (3, TRUE, '{"vi":"Luận văn thử nghiệm 1", "en": "Thesis test 1"}', 1)
  , (3, TRUE, '{"vi":"Luận văn thử nghiệm 2", "en": "Thesis test 2"}', 1)
  , (3, TRUE, '{"vi":"Luận văn thử nghiệm 3", "en": "Thesis test 3"}', 1)
ON CONFLICT DO NOTHING;
INSERT INTO tp_topic (created_at, deleted, updated_at, description, document_reference, max_student_take, min_student_take, name, task, thesis, council_id, semester_id, subject_department_id) VALUES ('2021-11-02 00:21:19.937812', false, '2021-11-02 00:21:19.937812', 'Nghiên cứu quy trình bán hàng trong một cửa hàng bán lẻ, siêu thị từ đó xây dựng mô hình tự động hóa quy trình này thông qua ứng dụng di động. Nghiên cứu vận dụng các kỹ thuật/công nghệ IoT, tính toán di động, khai phá dữ liệu, học máy, AI, Big data trong các giải pháp đề xuất.', NULL, 3, 1, '{"en":"Building a mobile app for retail system","vi":"Xây dựng ứng dụng bán hàng bách hóa trên ứng dụng di động"}', 'Phân tích, thiết kế và xây dựng ứng dụng trên di động hỗ trợ hoạt động của các cửa hàng bách hóa với các chức năng sau:
- Tìm hiểu về mô hình thanh toán điện tử, không tiền mặt (ví dụ qua các ví điện tử với QR code,...)
- Nghiên cứu các quy trình tạo ra giỏ hàng: tạo trên mobile app (ví dụ khách hàng đã tạo trước từ nhà thông qua mobile app), tạo bằng cách khách hàng tự chọn và quét mã vạch của mặt hàng kèm số lượng của mỗi mặt hàng tại cửa hàng (thực hiện trên điện thoại của khách hàng), người bán hàng tạo giỏ hàng bằng cách quét mã vạch trên điện thoại của mình.
- Xây dựng các kịch bản thanh toán bao gồm: thanh toán điện tử, thanh toán tiền mặt tại quầy,... và đề xuất quy trình tối thiểu hóa các bước trong thanh toán.	
- Xây dựng hệ thống thử nghiệm và đánh giá giải pháp đề xuất.
', false, NULL, 2, 1);
INSERT INTO tp_topic (created_at, deleted, updated_at, description, document_reference, max_student_take, min_student_take, name, task, thesis, council_id, semester_id, subject_department_id) VALUES ('2021-11-02 00:37:31.949887', false, '2021-11-02 00:37:31.949887', 'Phân tích, thiết kế và xây dựng ứng dụng hỗ trợ dạy học trực tuyến.', NULL, 3, 1, '{"en":"Building an online training management system","vi":"Xây dựng ứng dụng hỗ trợ dạy học trực tuyến"}', 'Phân tích, thiết kế và xây dựng ứng dụng hỗ trợ dạy học trực tuyến với các chức năng sau:
- Cho phép giảng viên quản lý lớp học, tài liệu (bài giảng, bài tập, bài thi, bài kiểm tra,... cho các lớp học của mình)
- Học viên đăng ký môn học (với lớp học cụ thể) để học và nhận thông báo từ môn học đó và của lớp liên quan.
- Học viên có thể xem lý thuyết, bài giảng (online + offline), làm bài tập, xem video về môn học đã đăng ký.
- Hệ thống còn cung cấp các tính năng bổ trợ cho việc học: Bảng xếp hạng thành viên, lịch sử, download tài liệu để xem offline, Danh sách các đáp án của các câu hỏi và có thể bình luận để trao đổi về đáp án, thống kê lại quá trình học tập của người dùng.
- Tích hợp hệ thống, thử nghiệm và đánh giá các giải pháp đề xuất.
', true, NULL, 2, 1);
INSERT INTO tp_topic (created_at, deleted, updated_at, description, document_reference, max_student_take, min_student_take, name, task, thesis, council_id, semester_id, subject_department_id) VALUES ('2021-11-02 00:38:15.449895', false, '2021-11-02 00:38:15.449895', 'Dữ liệu lớn hiện tại được lấy và phát sinh từ nhiều nguồn khác nhau, và sự đa dạng dữ liệu này khiến cho việc phân tích dữ liệu trở nên khó khăn. Bước chuyển đổi dữ liệu giữa các định dạng là vô cùng quan trọng để đồng bộ dữ liệu trước khi xử lý. Trong quá trình chuyển đổi này, chúng ta cần một công cụ rà soát và kiểm tra tính đúng sai, toàn vẹn, … giữa tập dữ liệu nguồn và tập dữ liệu được chuyển đổi. Trong bài toán này, sinh viên sẽ cần tìm hiểu quy trình chuyển đổi dữ liệu và xây dựng công cụ Validation cho quy trình này.', NULL, 3, 1, '{"en":"Validation Module for Data Conversion System between RDBMS and NoSQL ","vi":"Xây dựng Công cụ Validation cho quá trình chuyển đổi dữ liệu giữa RDBMS và NoSQL"}', 'Tìm hiểu về các mô hình CSDL
Tìm hiểu các framework để chuyển đổi dữ liệu
Tìm hiểu các framework xử lý dữ liệu lớn
Tìm hiểu về các bài toán Validation trong chuyển đổi dữ liệu
Xây dựng công cụ Validation cho bài toán chuyển đổi dữ liệu
Nâng cao hiệu suất các giải pháp hiện có
Hiện thực bài toán
Thử nghiệm và đánh giá giải pháp
', false, NULL, 2, 1);
INSERT INTO tp_topic (created_at, deleted, updated_at, description, document_reference, max_student_take, min_student_take, name, task, thesis, council_id, semester_id, subject_department_id) VALUES ('2021-11-02 00:38:50.404823', false, '2021-11-02 00:38:50.404823', 'Trái với cách học truyền thống khi các mô hình được xây dựng từ đầu với một giải thuật học cố định, cách học theo meta-learning nhằm mục đích cải thiện chính giải thuật học dựa theo một tập kinh nghiệm đã có. Luận văn này nghiên cứu về tiếp cận meta-learning trong các mô hình mạng nơ-ron phổ biến hiện nay: một số phương pháp và ứng dụng trong thị giác máy tính, phân tích dữ liệu.', NULL, 3, 1, '{"en":"Meta-learning in neural networks for classification of missing datasets","vi":"Tiếp cận học cách học trong các kiến trúc mạng nơ-ron cho các ứng dụng phân lớp với dữ liệu bị thiếu"}', '- Tìm hiểu tổng quan về tiếp cận học chuyển tiếp (transfer learning), tập trung vào hướng học cách học (meta-learning).
- Tìm các phương pháp phổ biến được quan tâm hiện nay, hiện thực và thực nghiệm các phương pháp này, phân tích/đánh giá.
- Đề xuất một lược đồ sử dụng meta-learning áp dụng cho bài toán phân lớp dữ liệu kích thước nhỏ và bị thiếu.
- Hiện thực và thực nghiệm với tập dữ liệu hình ảnh sản xuất trong công nghiệp.
- Phân tích và đánh giá kết quả. Demo.
', true, NULL, 2, 1);
INSERT INTO tp_topic (created_at, deleted, updated_at, description, document_reference, max_student_take, min_student_take, name, task, thesis, council_id, semester_id, subject_department_id) VALUES ('2021-11-02 00:40:38.118708', false, '2021-11-02 00:40:38.118708', 'Phát triển tiếp kết quả của nhóm đề tài K16 để xây dựng được thư viện và module điều khiển bay cho quadcopter có khả năng tích hợp thêm các cảm biến và module giao tiếp khác.
Hiện thực được ứng dụng điều khiển 2-3 quadcopter hoạt động theo mô hình bầy đàn
', NULL, 3, 1, '{"en":"Developing A Flocking Control for a Swarm Drones","vi":"Phát triển mô hình điều khiển thiết bị bay theo cơ chế bầy đàn"}', 'Tìm hiểu kiến thức nền tảng về mô hình SWARM Robot (cụ thể cho drones) dựa trên báo cáo của nhóm sinh viên K16.
Tìm hiểu phần cứng và phương pháp điều khiển quadcopter (được cung cấp sẵn, sinh viên không phải thiết kế và hiện thực phần cứng robot)
Thực hiện các thử nghiệm trên mô hình phần cứng đã có để điều khiển.
Đề xuất các công việc sẽ thực hiện trong giai đoạn luận văn dựa trên cơ sở phát triển kết quả của nhóm đề tài K16.
', true, NULL, 2, 1);
INSERT INTO tp_topic (created_at, deleted, updated_at, description, document_reference, max_student_take, min_student_take, name, task, thesis, council_id, semester_id, subject_department_id) VALUES ('2021-11-02 00:41:11.330047', false, '2021-11-02 00:41:11.330047', 'Trong thời đại hiện nay, nhu cầu cần được giải trí của con người đang rất thịnh hành. Có khá nhiều loại hình giải trí cho con người, trong đó âm nhạc là một trong những sự lựa chọn phổ biến nhất. Tuy nhiên hiện nay, danh sách phát nhạc (playlist) thường được con người tạo ra bằng cách thêm những bài nhạc yêu thích của họ vào trong playlist. Do đó, trong đề tài này, nhóm đề xuất xây dựng một máy phát nhạc, có thể tự động tạo ra một playlist phát nhạc thông qua cảm xúc được thể hiện trên khuôn mặt của con người. Không những thế, người dùng còn có thể điều khiển trạng thái phát nhạc, cũng như lựa chọn bài hát trong playlist bằng giọng nói hoặc các hành động của cơ thể. Về máy phát nhạc, về phần cứng của nó là các board nhúng được tích hợp cùng với các mô hình học máy, học sâu để phục vụ cho mục đích của bài toán.', NULL, 3, 1, '{"en":"Developing An Emotional Music Player based on Facial Emotion Recognition","vi":"Thiết kế và hiện thực thiết bị phát nhạc thông qua cảm xúc khuôn mặt"}', 'Tìm hiểu các giải thuật về đồ họa máy tính, thị giác máy tính và học sâu được sử dụng trong bài toán Nhận diện cảm xúc khuôn mặt (facial emotion recognition).
Tìm hiểu về các board nhúng và các thiết bị ngoại vi cần dùng trong sản phẩm.
Tìm hiểu, xây dựng và phát triển phần cứng một máy nghe nhạc có kích thước phù hợp; kiểu dáng, mẫu mã đẹp; có năng suất như các máy nghe nhạc ngoài thị trường.
Tìm hiểu cách tích hợp các module học máy, học sâu vào các board nhúng
Thực hiện thử nghiệm và đánh giá tính khả thi của các thiết bị
Đề xuất mô hình và đặc tả chi tiết ứng dụng sẽ phát triển trong giai đoạn LVTN
', true, NULL, 2, 1);
INSERT INTO tp_topic (created_at, deleted, updated_at, description, document_reference, max_student_take, min_student_take, name, task, thesis, council_id, semester_id, subject_department_id) VALUES ('2021-11-02 00:41:45.708994', false, '2021-11-02 00:41:45.708994', 'Hiện thực một robot tự hành có khả năng tự tìm đường đến đích khi vận hành trong một môi trường không xác định', 'https://www.ros.org/
http://gazebosim.org/
Slam algorithms
https://github.com/Introduction-to-Autonomous-Robots/Introduction-to-Autonomous-Robots ', 3, 1, '{"en":"Path-planning problem for autonomous robot in the environment of uncertainly.","vi":" Tìm đường của robot tự hành trong môi trường không chắc chắn"}', 'Tìm hiểu về ROS, làm quen với mô phỏng trên Gazebo và TurtleBot 3, 
Tìm hiểu công nghệ Lidar, camera, giải thuật SLAM, và Plan-planning
Làm quen với OpenCV và CNN.
Tích hợp giải thuật tìm đường đi (đề xuất từ GV hướng dẫn) vào hệ thống mô phỏng robot (robot thật ở giai đoạn luận văn)
', false, NULL, 2, 1);
INSERT INTO tp_topic (created_at, deleted, updated_at, description, document_reference, max_student_take, min_student_take, name, task, thesis, council_id, semester_id, subject_department_id) VALUES ('2021-11-02 00:42:15.680948', false, '2021-11-02 00:42:15.680948', 'Hiện thực xe(robot) tự hành (prototype) có khả năng di chuyển theo tín hiệu đường và tránh vật cản di động.', 'Ros: https://www.ros.org/
Slam algorithms
http://gazebosim.org/
', 3, 1, '{"en":"Autonomous mobile","vi":"Xe tự hành không người lái"}', 'Tìm hiểu về ROS, công nghệ Lidar, camera.
Làm quen với mô phỏng trên Gazebo, giải thuật SLAM, OpenCV, và CNN
Thử nghiệm hệ thống xe tự lái trên môi trường mô phỏng Gazebo tích hợp ROS.
', true, NULL, 2, 1);
INSERT INTO tp_topic (created_at, deleted, updated_at, description, document_reference, max_student_take, min_student_take, name, task, thesis, council_id, semester_id, subject_department_id) VALUES ('2021-11-02 00:43:08.708316', false, '2021-11-02 00:43:08.708316', 'Robot mang theo drone tự hành, drone có khả năng cất cánh và hạ cánh xuống robot khi robot đang di chuyển. Ngoài ra drone còn có khả năng chụp ảnh và giao tiếp với robot chủ.

', 'Ros: https://www.ros.org/
http://gazebosim.org/
Autonomous Landing of a UAV on a Moving Platform Using Model Predictive Control
', 3, 1, '{"en":"Autonomous Landing of a Drone on a Moving Platform","vi":"Hỗ trợ drone (tự hành) hạ cánh trên sân di động  "}', 'Tìm hiểu về ROS, Drone, camera.
Làm quen với mô phỏng trên Gazebo
Thử nghiệm hệ thống trên môi trường mô phỏng Gazebo tích hợp ROS.', true, NULL, 2, 1);
INSERT INTO tp_topic (created_at, deleted, updated_at, description, document_reference, max_student_take, min_student_take, name, task, thesis, council_id, semester_id, subject_department_id) VALUES ('2021-11-02 00:43:38.66946', false, '2021-11-02 00:43:38.66946', 'Drone có khả năng nhận diện và tracking vật thể di động', 'Ros: https://www.ros.org/
http://gazebosim.org/
https://github.com/Introduction-to-Autonomous-Robots/Introduction-to-Autonomous-Robots ', 3, 1, '{"en":" Object tracking by autonomous drone ","vi":"Drone tự hành bay theo quỹ đạo của vật thể xác định"}', 'Tìm hiểu về ROS, Drone, camera.
Làm quen với mô phỏng trên Gazebo
Thử nghiệm hệ thống trên môi trường mô phỏng Gazebo tích hợp ROS.
', false, NULL, 2, 1);

INSERT INTO tp_topic_assign
  (topic_id, student_id, guide_teacher_id, review_teacher_id)
VALUES
    (1, 1, 1, 1)
  , (2, null, 1, 1)
  , (3, null, 1, null)
  , (4, 1, 1, null)
  , (5, null, 1, null)
  , (6, null, 1, null)
  , (7, null, 1, null)
  , (8, null, 1, null)
  , (9, null, 1, null)
  , (10, null, 1, null)
  , (11, null, 1, null)
  , (12, null, 1, null)
  , (13, null, 1, null)
  , (14, null, 1, null)
  , (15, null, 1, null)
  , (16, null, 1, null)
ON CONFLICT DO NOTHING;

INSERT INTO tp_topic_property
  (topic_id, major_id, education_method_id)
VALUES
    (1, 1, 1)
  , (1, 2, 2)
ON CONFLICT DO NOTHING;

INSERT INTO us_notification (receiver_id, message) VALUES
    (1, 'Tin nhắn thử nghiệm 1 <a href="https://datai-thesis-web.herokuapp.com/users/1">link</a>')
  , (1, 'Tin nhắn thử nghiệm 2 <a href="https://datai-thesis-web.herokuapp.com/semesters">link</a>')
  , (1, 'Tin nhắn thử nghiệm 3 <a href="https://datai-thesis-web.herokuapp.com/topics">link</a>')
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
  , (1, 'Phieu Danh Gia LVTN Nganh CS GVPB', 'Phiếu đánh giá LVTN gồm 2 phần: phần Đánh giá điểm của sinh viên thực hiện luận văn tốt nghiệp và phần Đánh giá dành cho chuẩn đầu ra của chương trình. Thầy/ Cô vui lòng đánh giá cho cả 2 phần')
  , (1, 'Phieu Danh Gia LVTN Nganh CS GVPB', 'Phiếu đánh giá LVTN gồm 2 phần: phần Đánh giá điểm của sinh viên thực hiện luận văn tốt nghiệp và phần Đánh giá dành cho chuẩn đầu ra của chương trình. Thầy/ Cô vui lòng đánh giá cho cả 2 phần')
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
  (semester_id, subject_department_id, reserve_date, start_time, end_time, location, note)
VALUES
    (2, 1, '2021-11-02', '00:58:00', '00:58:00', '101-H1', '123')
ON CONFLICT DO NOTHING;

INSERT INTO tp_council_member
  (council_id, role_id, member_id)
VALUES
    (1, 1, 1)
  , (1, 2, 6)
  , (1, 3, 7)
ON CONFLICT DO NOTHING;

UPDATE tp_topic SET council_id = 1 WHERE id = 4;