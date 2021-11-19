-- DROP CONTRAINT

ALTER TABLE tp_topic_assign ALTER COLUMN student_id DROP NOT NULL,
                            ALTER COLUMN guide_teacher_id DROP NOT NULL,
                            ALTER COLUMN review_teacher_id DROP NOT NULL;

-- INIT DATA

INSERT INTO sy_education_method (name) VALUES ('Chính quy'), ('Chất lượng cao') ON CONFLICT DO NOTHING;
INSERT INTO sy_major (name) VALUES ('Khoa học máy tính'), ('Kỹ thuật máy tính') ON CONFLICT DO NOTHING;
INSERT INTO sy_degree (name) VALUES ('Thạc sĩ'), ('Tiến sĩ'), ('PGS.TS') ON CONFLICT DO NOTHING;

INSERT INTO sy_subject_department (name) VALUES
    ('Hệ thống thông tin'), ('Công nghệ phần mềm'), ('Hệ thống và mạng')
  , ('Khoa học máy tính'), ('Kỹ thuật máy tính')
ON CONFLICT DO NOTHING;

INSERT INTO sy_semester
  (name, status, register_topic_start, register_topic_end, topic_start, topic_end, thesis_start, thesis_end)
VALUES
    ('201', 'USED', '2021-01-17T00:12:00', '2021-03-17T00:12:00', '2021-03-17T00:12:00', '2021-05-17T00:12:00', '2021-03-17T00:12:00', '2021-06-17T00:12:00')
  , ('211', 'USED', '2021-01-17T00:12:00', '2021-03-17T00:12:00', '2021-03-17T00:12:00', '2021-05-17T00:12:00', '2021-03-17T00:12:00', '2021-06-17T00:12:00')
  , ('212', 'USING', '2021-06-17T00:12:00', '2021-12-10T00:12:00', '2021-11-1T00:12:00', '2022-03-01T00:12:00', '2021-11-1T00:12:00', '2022-04-17T00:12:00')
  , ('213', null, null, null, null, null, null, null)
ON CONFLICT DO NOTHING;

INSERT INTO tp_council_role (name, min, max, display_order) VALUES
    ('Chủ tịch', 1, 1, 1)
  , ('Thư ký', 1, 1, 2)
  , ('Ủy viên', 3, 3, 3)
ON CONFLICT DO NOTHING;


-- us_user----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO us_user
  (permission, first_name, last_name, email)
VALUES
  ('ADMIN', '', 'Admin', 'admin@hcmut.edu.vn')
ON CONFLICT DO NOTHING;

INSERT INTO us_user
  (permission, first_name, last_name, email)
VALUES
    ('EDUCATION_STAFF', 'Giáo vụ', '1', 'giaovu1@hcmut.edu.vn')
  , ('EDUCATION_STAFF', 'Giáo vụ', '2', 'giaovu2@hcmut.edu.vn')
  , ('EDUCATION_STAFF', 'Giáo vụ', '3', 'giaovu3@hcmut.edu.vn')
  , ('EDUCATION_STAFF', 'Giáo vụ', '4', 'giaovu4@hcmut.edu.vn')
ON CONFLICT DO NOTHING;

INSERT INTO us_user
  (permission, subject_department_id, degree_id, first_name, last_name, email)
VALUES
    ('HEAD_SUBJECT_DEPARTMENT', 1, 3, 'Trưởng phòng ban', 'Hệ thống thông tin', 'headHTTT@hcmut.edu.vn')
  , ('HEAD_SUBJECT_DEPARTMENT', 2, 3, 'Trưởng phòng ban', 'Công nghệ phần mềm', 'headCNPM@hcmut.edu.vn')
  , ('HEAD_SUBJECT_DEPARTMENT', 3, 3, 'Trưởng phòng ban', 'Hệ thống và mạng', 'headHTVM@hcmut.edu.vn')
  , ('HEAD_SUBJECT_DEPARTMENT', 4, 3, 'Quản Thành', 'Thơ', 'qttho@hcmut.edu.vn')
  , ('HEAD_SUBJECT_DEPARTMENT', 5, 3, 'Trần Ngọc', 'Thịnh', 'tnthinh@hcmut.edu.vn')
ON CONFLICT DO NOTHING;

INSERT INTO us_user
  (permission, subject_department_id, degree_id, male, first_name, last_name, email)
VALUES
    ('TEACHER', 1, 1, true, 'Phạm Hoàng', 'Anh', 'anhpham@hcmut.edu.vn')
  , ('TEACHER', 1, 2, true, 'Phan Đình Thế', 'Duy', 'duypdt@hcmut.edu.vn')
  , ('TEACHER', 1, 3, true, 'Trần Thanh', 'Bình', 'thanhbinh@hcmut.edu.vn')
  , ('TEACHER', 1, 1, true, 'Trần văn', 'Hoài', 'vanhoai@hcmut.edu.vn')
  , ('TEACHER', 1, 1, true, 'Lê Trọng', 'Nhân', 'trongnhanle@hcmut.edu.vn')
  , ('TEACHER', 1, 1, true, 'Phạm Quốc', 'Cường', 'cuongpham@hcmut.edu.vn')
  , ('TEACHER', 1, 1, true, 'Nguyễn Trí', 'Đức', 'triduc@hcmut.edu.vn')
  , ('TEACHER', 2, 1, true, 'Nguyễn Xuân', 'Quang', 'nxquang@hcmut.edu.vn')
  , ('TEACHER', 2, 1, true, 'Lê Lam', 'Sơn', 'lamson@hcmut.edu.vn')
  , ('TEACHER', 3, 1, true, 'Băng Ngọc Bảo', 'Tâm', 'bnbaotam@hcmut.edu.vn')
  , ('TEACHER', 3, 1, true, 'Mai Đức', 'Trung', 'mdtrung@hcmut.edu.vn')
  , ('TEACHER', 3, 1, true, 'Nguyễn An', 'Khương', 'nakhuong@hcmut.edu.vn')
  , ('TEACHER', 4, 1, true, 'Nguyễn Tiến', 'Thịnh', 'ntthinh@hcmut.edu.vn')
  , ('TEACHER', 4, 1, true, 'Trương Tuấn', 'Anh', 'anhtt@hcmut.edu.vn')
  , ('TEACHER', 4, 1, false, 'Võ Thị Ngọc', 'Châu', 'chauvtn@hcmut.edu.vn')
  , ('TEACHER', 4, 1, false, 'Trương Quỳnh', 'Chi', 'tqchi@hcmut.edu.vn')
  , ('TEACHER', 4, 2, true, 'Phan Trọng', 'Nhân', 'nhanpt@hcmut.edu.vn')
  , ('TEACHER', 4, 1, true, 'Nguyễn Đình', 'Thành', 'dinhthanh@hcmut.edu.vn')
  , ('TEACHER', 4, 1, true, 'Trần', 'Quang', 'tranquang@hcmut.edu.vn')
  , ('TEACHER', 5, 1, false, 'Nguyễn Thị Ái', 'Thảo', 'thaonguyen@hcmut.edu.vn')
  , ('TEACHER', 5, 1, true, 'Nguyễn Đình', 'Thành', 'dinhthanh@hcmut.edu.vn')
  , ('TEACHER', 5, 1, false, 'Trần Thị Quế', 'Nguyệt', 'ttqnguyet@hcmut.edu.vn')
  , ('TEACHER', 5, 1, true, 'Nguyễn Thanh', 'Tùng', 'thanhtung@hcmut.edu.vn')
  , ('TEACHER', 5, 1, true, 'Võ Thị Hồng', 'Tuyết', 'hongtuyet@hcmut.edu.vn')
  , ('TEACHER', 5, 2, true, 'Trần Tuấn', 'Anh', 'tranh@hcmut.edu.vn')
  , ('TEACHER', 5, 2, true, 'Nguyễn Tiến', 'Thịnh', 'ntthinh@hcmut.edu.vn')
  , ('TEACHER', 5, 1, true, 'Trần Hồng', 'Tài', 'thtai@hcmut.edu.vn')
  , ('TEACHER', 5, 1, true, 'Trần Giang', 'Sơn', 'tgson@hcmut.edu.vn')
  , ('TEACHER', 5, 1, true, 'Trần Ngọc Bảo', 'Duy', 'duytnb@hcmut.edu.vn')
  , ('TEACHER', 5, 3, true, 'Huỳnh Tường', 'Nguyên', 'htnguyen@hcmut.edu.vn')
ON CONFLICT DO NOTHING;

INSERT INTO us_user
  (permission, education_method_id, major_id, male, first_name, last_name, email)
VALUES
    ('STUDENT', 1, 1, true, 'Nguyễn', 'q', 'q@hcmut.edu.vn')
  , ('STUDENT', 1, 1, true, 'Nguyễn', 'w', 'w@hcmut.edu.vn')
  , ('STUDENT', 1, 1, true, 'Nguyễn', 'e', 'e@hcmut.edu.vn')
  , ('STUDENT', 1, 1, true, 'Nguyễn', 'r', 'r@hcmut.edu.vn')
  , ('STUDENT', 1, 1, true, 'Nguyễn', 't', 't@hcmut.edu.vn')
  , ('STUDENT', 1, 1, true, 'Nguyễn', 'y', 'y@hcmut.edu.vn')
  , ('STUDENT', 1, 1, true, 'Nguyễn', 'u', 'u@hcmut.edu.vn')
  , ('STUDENT', 1, 2, true, 'Nguyễn', 'i', 'i@hcmut.edu.vn')
  , ('STUDENT', 1, 2, true, 'Nguyễn', 'o', 'o@hcmut.edu.vn')
  , ('STUDENT', 1, 2, true, 'Nguyễn', 'p', 'p@hcmut.edu.vn')
  , ('STUDENT', 1, 2, false, 'Nguyễn', 'a', 'a@hcmut.edu.vn')
  , ('STUDENT', 1, 2, false, 'Nguyễn', 's', 's@hcmut.edu.vn')
  , ('STUDENT', 1, 2, false, 'Nguyễn', 'd', 'd@hcmut.edu.vn')
  , ('STUDENT', 2, 1, false, 'Nguyễn', 'f', 'f@hcmut.edu.vn')
  , ('STUDENT', 2, 1, false, 'Nguyễn', 'g', 'g@hcmut.edu.vn')
  , ('STUDENT', 2, 1, false, 'Nguyễn', 'h', 'h@hcmut.edu.vn')
  , ('STUDENT', 2, 1, true, 'Nguyễn', 'j', 'j@hcmut.edu.vn')
  , ('STUDENT', 2, 1, true, 'Nguyễn', 'k', 'k@hcmut.edu.vn')
  , ('STUDENT', 2, 2, true, 'Nguyễn', 'l', 'l@hcmut.edu.vn')
  , ('STUDENT', 2, 2, true, 'Nguyễn', 'z', 'z@hcmut.edu.vn')
  , ('STUDENT', 2, 2, true, 'Nguyễn', 'x', 'x@hcmut.edu.vn')
  , ('STUDENT', 2, 2, true, 'Nguyễn', 'c', 'c@hcmut.edu.vn')
  , ('STUDENT', 2, 2, true, 'Nguyễn', 'v', 'v@hcmut.edu.vn')
  , ('STUDENT', 2, 2, true, 'Nguyễn', 'b', 'b@hcmut.edu.vn')
  , ('STUDENT', 2, 2, true, 'Nguyễn', 'n', 'n@hcmut.edu.vn')
  , ('STUDENT', 2, 2, true, 'Nguyễn', 'm', 'm@hcmut.edu.vn')
ON CONFLICT DO NOTHING;

UPDATE us_user SET code = id;

-- tp_topic_1637140938823-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:09:11.047771','false','2021-11-17 16:09:11.047771','Phát triển tiếp kết quả của nhóm đề tài K16 để xây dựng được thư viện và module điều khiển bay cho quadcopter có khả năng tích hợp thêm các cảm biến và module giao tiếp khác.
Hiện thực được ứng dụng điều khiển 2-3 quadcopter hoạt động theo mô hình bầy đàn. 
','Liên hệ GVHD','2','1','{"en":" Developing A Flocking Control for a Swarm Drones","vi":" Phát triển mô hình điều khiển thiết bị bay theo cơ chế bầy đàn"}','Tìm hiểu kiến thức nền tảng về mô hình SWARM Robot (cụ thể cho drones) dựa trên báo cáo của nhóm sinh viên K16.
Tìm hiểu phần cứng và phương pháp điều khiển quadcopter (được cung cấp sẵn, sinh viên không phải thiết kế và hiện thực phần cứng robot)
Thực hiện các thử nghiệm trên mô hình phần cứng đã có để điều khiển.
Đề xuất các công việc sẽ thực hiện trong giai đoạn luận văn dựa trên cơ sở phát triển kết quả của nhóm đề tài K16.
','false',null,'3','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:10:01.537353','false','2021-11-17 16:10:01.537353','Trong thời đại hiện nay, nhu cầu cần được giải trí của con người đang rất thịnh hành. Có khá nhiều loại hình giải trí cho con người, trong đó âm nhạc là một trong những sự lựa chọn phổ biến nhất. Tuy nhiên hiện nay, danh sách phát nhạc (playlist) thường được con người tạo ra bằng cách thêm những bài nhạc yêu thích của họ vào trong playlist. Do đó, trong đề tài này, nhóm đề xuất xây dựng một máy phát nhạc, có thể tự động tạo ra một playlist phát nhạc thông qua cảm xúc được thể hiện trên khuôn mặt của con người. Không những thế, người dùng còn có thể điều khiển trạng thái phát nhạc, cũng như lựa chọn bài hát trong playlist bằng giọng nói hoặc các hành động của cơ thể. Về máy phát nhạc, về phần cứng của nó là các board nhúng được tích hợp cùng với các mô hình học máy, học sâu để phục vụ cho mục đích của bài toán.',null,'3','1','{"en":"Developing An Emotional Music Player based on Facial Emotion Recognition","vi":"Thiết kế và hiện thực thiết bị phát nhạc thông qua cảm xúc khuôn mặt"}','Tìm hiểu các giải thuật về đồ họa máy tính, thị giác máy tính và học sâu được sử dụng trong bài toán Nhận diện cảm xúc khuôn mặt (facial emotion recognition).
Tìm hiểu về các board nhúng và các thiết bị ngoại vi cần dùng trong sản phẩm.
Tìm hiểu, xây dựng và phát triển phần cứng một máy nghe nhạc có kích thước phù hợp; kiểu dáng, mẫu mã đẹp; có năng suất như các máy nghe nhạc ngoài thị trường.
Tìm hiểu cách tích hợp các module học máy, học sâu vào các board nhúng
Thực hiện thử nghiệm và đánh giá tính khả thi của các thiết bị
Đề xuất mô hình và đặc tả chi tiết ứng dụng sẽ phát triển trong giai đoạn LVTN
','false',null,'3','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:10:51.47219','false','2021-11-17 16:10:51.47219','Hiện thực một robot tự hành có khả năng tự tìm đường đến đích khi vận hành trong một môi trường không xác định','https://www.ros.org/
http://gazebosim.org/
Slam algorithms
https://github.com/Introduction-to-Autonomous-Robots/Introduction-to-Autonomous-Robots 
','3','1','{"en":"Path-planning problem for autonomous robot in the environment of uncertainly.","vi":"Tìm đường của robot tự hành trong môi trường không chắc chắn"}','Tìm hiểu về ROS, làm quen với mô phỏng trên Gazebo và TurtleBot 3, 
Tìm hiểu công nghệ Lidar, camera, giải thuật SLAM, và Plan-planning
Làm quen với OpenCV và CNN.
Tích hợp giải thuật tìm đường đi (đề xuất từ GV hướng dẫn) vào hệ thống mô phỏng robot (robot thật ở giai đoạn luận văn)
','false',null,'3','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:11:24.937135','false','2021-11-17 16:11:24.937135','Hiện thực xe(robot) tự hành (prototype) có khả năng di chuyển theo tín hiệu đường và tránh vật cản di động.','Ros: https://www.ros.org/
Slam algorithms
http://gazebosim.org/
https://github.com/Introduction-to-Autonomous-Robots/Introduction-to-Autonomous-Robots 
','3','1','{"en":"Autonomous mobile ","vi":"Xe tự hành không người lái"}','Tìm hiểu về ROS, công nghệ Lidar, camera.
Làm quen với mô phỏng trên Gazebo, giải thuật SLAM, OpenCV, và CNN
Thử nghiệm hệ thống xe tự lái trên môi trường mô phỏng Gazebo tích hợp ROS.
','false',null,'3','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:12:07.941267','false','2021-11-17 16:12:07.941267','	Robot mang theo drone tự hành, drone có khả năng cất cánh và hạ cánh xuống robot khi robot đang di chuyển. Ngoài ra drone còn có khả năng chụp ảnh và giao tiếp với robot chủ.','Ros: https://www.ros.org/
http://gazebosim.org/
Autonomous Landing of a UAV on a Moving Platform Using Model Predictive Control
','3','1','{"en":"Autonomous Landing of a Drone on a Moving Platform","vi":"Hỗ trợ drone (tự hành) hạ cánh trên sân di động  "}','Tìm hiểu về ROS, Drone, camera.
Làm quen với mô phỏng trên Gazebo
Thử nghiệm hệ thống trên môi trường mô phỏng Gazebo tích hợp ROS.
','false',null,'3','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:12:49.483065','false','2021-11-17 16:12:49.483065','Drone có khả năng nhận diện và tracking vật thể di động','Ros: https://www.ros.org/
http://gazebosim.org/
https://github.com/Introduction-to-Autonomous-Robots/Introduction-to-Autonomous-Robots 
','3','1','{"en":"Object tracking by autonomous drone ","vi":"Drone tự hành bay theo quỹ đạo của vật thể xác định"}','Tìm hiểu về ROS, Drone, camera.
Làm quen với mô phỏng trên Gazebo
Thử nghiệm hệ thống trên môi trường mô phỏng Gazebo tích hợp ROS.
','false',null,'3','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:13:35.479388','false','2021-11-17 16:13:35.479388','Xây dựng và triển khai hệ thống bảo mật dựa trên AI cho mạng máy tính dựa trên kiến trúc mạng định nghĩa bằng phần mềm. Để tăng hiệu suất của hê thống này, nền tảng NetFPGA-Sume, một nền tảng mới nhất của thiết kế mạng tốc độ cao 10G, sử dụng ngôn ngữ cấp cao để thiết kế, tổng hợp phần cứng nhanh hơn (HLS).
','Liên hệ GVHD','3','2','{"en":"AI-based security Deployment for Software Defined Network (SDN) on NetFPGA-SUME","vi":"Triển khai hệ thống bảo mật dựa trên AI cho mạng định nghĩa phần mềm SDN trên NetFPGA-SUME"}','Giai đoạn đề cương:
Tìm hiểu về kiến trúc mạng định nghĩa phần mềm (SDN), các giao thức cơ bản của OpenFlow
Tổng hợp các thuật toán AI liên quan bảo mật về phát hiện xâm nhập mạng dựa trên các dấu hiệu bất thường. Bên cạnh đó, đưa ra các tập dữ liệu phù hợp với môi trường hệ thống.Lựa chọn giải thuật AI và tập dữ liệu phù hợp.
Nghiên cứu thiết bị phần cứng hỗ trợ, phần mềm giả lập khả cấu hình NetFPGA-SUME
Giai đoạn luận văn:
Mô phỏng và hiện thực các cấu trúc cơ bản của hệ thống mạng SDN với phần cứng hỗ trợ NetFPGA-SUME. 
Kiểm thử hệ thống đề xuất hoạt động giữa hai lớp điều khiển Controller và lớp dữ liệu dataplane. 
Kiểm thử các đề xuất về thuật toán AI và tập dữ liệu trên phần mềm.
Hiện thực mô hình kiểm thử ở phần mềm lên phần cứng nhằm tích hợp hệ thống bảo mật cho SDN.
Xây dựng mô hình kiểm thử hệ thống đề xuất thiết kế, và hoàn chỉnh hệ thống
','false',null,'3','5');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:15:53.484501','false','2021-11-17 16:15:53.484501','Smart parking is an important element in the development of smart cities. This project aims at designing and implementing a hardware/software co-design architecture for accelerating AI algorithms in Edge Computing platforms such as FPGA or System-on-chip (SoC), thereby applying to smart parking environment.','K. Hassoune, W. Dachry, F. Moutaouakkil and H. Medromi, "Smart parking systems: A survey," 2016 11th International Conference on Intelligent Systems: Theories and Applications (SITA), 2016, pp. 1-6, doi: 10.1109/SITA.2016.7772297.
Li, Wenbin & Liewig, Matthieu. (2020). A Survey of AI Accelerators for Edge Environment. 10.1007/978-3-030-45691-7_4.
','3','3','{"en":"An Edge AI Hardware Acceleration Solution for Smart Parking Environment","vi":null}','  Phase 1: Pre-Thesis 
Study about Smart Parking, Edge Computing, and their roles in Smart Cities.
Do a literature review about existing smart parking solutions and related technologies.
Do a literature review and tool exploration about Edge AI platforms (FPGA or SoC) satisfied with Smart Parking environment.
Study on Edge AI Hardware Acceleration techniques and technologies, which can be used in Smart Parking systems.
Study on AI algorithms for Smart Parking tasks: 
Determine the number of parked vehicles and empty parking spaces in a parking lot from surveillance cameras.
Detect parking lots line or region of interest (ROI) 
Build a real-time license plate detection solution for parking access
Do experiments to exploit the existing solutions.
  Phase 2: Thesis
Design and implement an Edge AI Hardware Acceleration solution for Smart Parking tasks.
Integrate the Edge AI solution with Cloud Server and User Applications
Implement testbed to test the proposed system.
Evaluate performance and deploy the system in practical environment.  
','false',null,'3','5');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:16:57.523767','false','2021-11-17 16:16:57.523767','Xây dựng môi trong mô phỏng cho việc truyền nhận dữ liệu LoRa, đo đạc các thông số liên quan đến năng lượng, thời gian hoạt động, đụng độ dữ liệu, mô phỏng năng lượng mặt trời cho việc cấp nguồn của hệ thống.
Hiện thực một ứng dụng thử nghiệm việc giao tiếp giữa các nốt LoRa thật để đối chiếu với môi trường mô phỏng
','Liên hệ GVHD','2','1','{"en":"Develop LoRa Mesh Simulation Environment on OMNET++","vi":"Xây dựng môi trường mô phỏng LoRa Mesh trên OMNET++"}','Tìm hiểu công cụ mô phỏng OMNET++
Tìm hiểu kĩ thuật gửi nhận dữ liệu không dây trên OMNET++
Hiện thực mô hình mô phỏng giao tiếp LoRa
Hiện thực mô hình mô phỏng năng lượng 
Đề xuất mô hình và đặc tả chi tiết ứng dụng sẽ phát triển trong giai đoạn LVTN
','false',null,'3','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:17:32.663316','false','2021-11-17 16:17:32.663316','Đối với giao tiếp LoRa, mặc dù có lợi thế trong việc giao tiếp xa, nhưng dữ liệu trong gói tin LoRa là hạn chế về kích thước do vấn đề năng lượng của kênh truyền. Do đó, các bài toán liên quan đến xử lý ảnh (computer vision) cần phải được xử lý tại biên trước khi gửi kết quả về Gateway.',null,'2','1','{"en":"Edge Computing Applied for LoRa based Application","vi":"Tính toán biên cho một ứng dụng dựa trên LoRa"}','Tìm hiểu mạng cảm biến sử dụng giao tiếp LoRa
Tìm hiểu các thiết bị camera có thể áp dụng cho bài toán xử lý ảnh
Tìm hiểu các model AI có thể áp dụng vào hệ thống có tài nguyên hạn chế
Hiện thực thử nghiệm bài toán nhận điện bằng AI Model
Đề xuất ứng dụng sẽ thực hiện trong giai đoạn luận văn 
','false',null,'3','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:19:13.001399','false','2021-11-17 16:19:13.001399','Nhu cầu và xu hướng tất yếu trong vấn đề theo dõi sức khỏe từ xa là tất yếu trong hiện tại cũng như tương lai. Hai chỉ số Oxy máu và ECG là các chỉ số sức khỏe quan trọng trong tầm soát và tiên đoán những dấu hiệu về sức khỏe cần có sự can thiệp của bác sĩ.
Hiện thực hệ thống theo dõi 2 chỉ số Oxy máu và ECG trực tuyến (online) và từ xa (remote) sẽ đem đến nhiều giá trị thực tiễn về cả lĩnh vực y tế và đóng góp trong mảng tích hợp công nghệ vào đời sống, sức khỏe cộng đồng.
','Liên hệ GVHD','3','3','{"en":"Online Blood oxygen and ECG monitoring system.","vi":"Thiết kế và chế tạo thiết bị theo dõi Oxy máu và ECG online."}','Tìm hiểu kiến thức nền tảng về Oxy máu và ECG cơ bản.
Tìm hiểu phần cứng và phương pháp giao tiếp với các hệ thống cảm biến, thiết bị chuyên dụng đo Oxy máu, ECG.
Thực hiện các thử nghiệm trên mô hình phần cứng đã có để khảo sát, đánh giá, tiền xử lý dữ liệu.
Xây dựng mô hình ứng dụng, và kịch bản ứng dụng cơ bản đo Oxy máu và ECG online.
','false',null,'3','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:18:12.250712','false','2021-11-17 16:18:12.250712','Đối với ứng dụng chăm sóc sức khỏe hiện tại, hệ thống nhận diện khuôn mặt đóng vai trò then chốt đối với hệ thống. Luận văn hướng đến việc cải tiến hệ thống nhận diện khuôn mặt hiện tại, nâng cao độ chính xác với các giải pháp phần mềm lẫn phần cứng.',null,'2','1','{"en":"Improve face recognition system in eHealth application","vi":"Cải tiến hệ thống nhận dạng khuôn mặt trong ứng dụng sức khỏe điện tử"}','Tìm hiểu hệ thống nhận dạng khuôn mặt trên Jetson Nano
Chạy ứng dụng nhận diện khuôn mặt
Đề xuất các cơ chế cải tiến
Hiện thực thử nghiệm 
Đề xuất ứng dụng sẽ thực hiện trong giai đoạn luận văn 
','false',null,'3','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:20:02.222099','false','2021-11-17 16:20:02.222099','Đề tài tập trung vào phân tích mã nguồn của Openpiton RISC-V SoC để tìm các lỗ hỏng bảo mật mức phần cứng và đề xuất các giải pháp khắc phục',null,'3','1','{"en":"RISC-V SoC HW security testing with HDL analysis","vi":"Kiểm thử bảo mật phần cứng của RISC-V SoC bằng việc phân tích mã nguồn RTL"}','Dựng lại môi trường mô phỏng của openpiton.
Đọc và phân tích Verilog source code, từ đó dựng lại block diagram cho toàn bộ SoC và cho từng module của hệ thống.
Đào sâu vào từng module để tìm những lỗ hổng trong hiện thực phần cứng. Nếu tìm thấy lỗ hổng:
Dựng testbench để chứng minh lỗ hổng tìm được.
Viết user application bằng C để khai thác lỗ hổng từ phần mềm.
Đánh giá độ nguy hiểm của lỗi.
Đưa ra đề xuất để vá những lỗi tìm được, có thể đưa ra những tình huống mà lỗi có thể xảy ra trong thực tế
Tổng hợp lại về hardware bugs, hardware attack patterns, ...
','false',null,'3','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:20:51.562302','false','2021-11-17 16:20:51.562302','Đề tài tập trung vào tìm hiểu và phân tích mạng nơ-ron học sâu và đề xuất phương pháp tăng tốc độ xử lý giai đoạn suy luận (inference) bằng cách kết hợp phần cứng tái cấu hình FPGA với các bộ xử lý truyền thống. Đề tài tập trung vào việc phát triển lõi tính toán phục vụ cho việc xử lý mạng nơ-ron trên FPGA đồng thời phát triển hệ thống SoC kết hợp phần cứng phần mềm để thực thi hệ thống và đo đạc số liệu liên quan đến hiệu suất.',null,'3','2','{"en":"RISC-V SoC HW security testing with HDL analysis","vi":"Tăng tốc mạng nơ-ron học sâu với phần cứng tái cấu hình"}','Tìm hiểu mạng nơ-ron CNN.
Phân tích hoạt động tìm hiểu các model phù hợp cho các nền tảng phần cứng tái cấu hình/điện toán biên
Phát triễn lõi tính toán (core) phù hợp cho việc tăng tốc mạng nơ-ron
Xây dựng hệ thống SoC kết hợp phần cứng FGPA và phần mềm trên CPU
Phát triển ứng dụng kiểm thử
Đo đạc và tính toán hiệu suất xử lý.
','false',null,'3','1');

-- tp_topic_education_methods_1637226599520-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into tp_topic_education_methods(topic_id,education_method_id) values('1','2');
insert into tp_topic_education_methods(topic_id,education_method_id) values('1','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('2','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('2','2');
insert into tp_topic_education_methods(topic_id,education_method_id) values('3','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('4','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('5','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('5','2');
insert into tp_topic_education_methods(topic_id,education_method_id) values('6','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('7','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('8','2');
insert into tp_topic_education_methods(topic_id,education_method_id) values('9','2');
insert into tp_topic_education_methods(topic_id,education_method_id) values('10','2');
insert into tp_topic_education_methods(topic_id,education_method_id) values('10','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('11','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('11','2');
insert into tp_topic_education_methods(topic_id,education_method_id) values('12','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('13','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('14','1');
insert into tp_topic_education_methods(topic_id,education_method_id) values('14','2');


-- tp_topic_majors_1637227003638-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into tp_topic_majors(topic_id,major_id) values('1','1');
insert into tp_topic_majors(topic_id,major_id) values('1','2');
insert into tp_topic_majors(topic_id,major_id) values('2','2');
insert into tp_topic_majors(topic_id,major_id) values('3','2');
insert into tp_topic_majors(topic_id,major_id) values('4','2');
insert into tp_topic_majors(topic_id,major_id) values('5','2');
insert into tp_topic_majors(topic_id,major_id) values('6','2');
insert into tp_topic_majors(topic_id,major_id) values('7','1');
insert into tp_topic_majors(topic_id,major_id) values('7','2');
insert into tp_topic_majors(topic_id,major_id) values('8','2');
insert into tp_topic_majors(topic_id,major_id) values('9','2');
insert into tp_topic_majors(topic_id,major_id) values('10','1');
insert into tp_topic_majors(topic_id,major_id) values('10','2');
insert into tp_topic_majors(topic_id,major_id) values('11','2');
insert into tp_topic_majors(topic_id,major_id) values('12','2');
insert into tp_topic_majors(topic_id,major_id) values('13','2');
insert into tp_topic_majors(topic_id,major_id) values('14','2');


-- tp_topic_guide_teachers-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('11','1');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('12','2');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('11','2');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('13','3');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('13','4');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('13','5');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('14','5');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('13','6');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('10','7');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('10','8');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('15','9');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('15','10');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('15','11');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('12','12');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('16','13');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('17','13');
insert into tp_topic_guide_teachers(guide_teacher_id,topic_id) values('16','14');

-- us_notification-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO us_notification (receiver_id, message) VALUES
    (1, 'Tin nhắn thử nghiệm 1 <a href="https://datai-thesis-web.herokuapp.com/users/1">link</a>')
  , (1, 'Tin nhắn thử nghiệm 2 <a href="https://datai-thesis-web.herokuapp.com/semesters">link</a>')
  , (1, 'Tin nhắn thử nghiệm 3 <a href="https://datai-thesis-web.herokuapp.com/topics">link</a>')
ON CONFLICT DO NOTHING;
INSERT INTO us_notification (receiver_id, message)
  SELECT id, 'Kết thúc thời gian đăng ký đề tài. <a target="_blank" href="http://localhost:3000/my/topics">chi tiết</a>'
  FROM us_user;

