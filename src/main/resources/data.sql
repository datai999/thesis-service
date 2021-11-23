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
  , ('212', 'USING', '2021-06-17T00:12:00', '2021-11-10T00:12:00', '2021-11-1T00:12:00', '2022-03-01T00:12:00', '2021-11-1T00:12:00', '2022-04-17T00:12:00')
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
','false',null,'2','1');
insert into tp_topic(created_at,deleted,updated_at,description,document_reference,max_student_take,min_student_take,name,task,thesis,council_id,semester_id,subject_department_id) values('2021-11-17 16:20:02.222099','false','2021-11-17 16:20:02.222099','Đề tài tập trung vào phân tích mã nguồn của Openpiton RISC-V SoC để tìm các lỗ hỏng bảo mật mức phần cứng và đề xuất các giải pháp khắc phục',null,'3','1','{"en":"RISC-V SoC HW security testing with HDL analysis","vi":"Kiểm thử bảo mật phần cứng của RISC-V SoC bằng việc phân tích mã nguồn RTL"}','Dựng lại môi trường mô phỏng của openpiton.
Đọc và phân tích Verilog source code, từ đó dựng lại block diagram cho toàn bộ SoC và cho từng module của hệ thống.
Đào sâu vào từng module để tìm những lỗ hổng trong hiện thực phần cứng. Nếu tìm thấy lỗ hổng:
Dựng testbench để chứng minh lỗ hổng tìm được.
Viết user application bằng C để khai thác lỗ hổng từ phần mềm.
Đánh giá độ nguy hiểm của lỗi.
Đưa ra đề xuất để vá những lỗi tìm được, có thể đưa ra những tình huống mà lỗi có thể xảy ra trong thực tế
Tổng hợp lại về hardware bugs, hardware attack patterns, ...
','false',null,'2','1');
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


-- tp_guide_teacher-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','11','1');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('false','6','1');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','12','2');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('false','11','2');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','13','3');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','13','4');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','13','5');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('false','14','5');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','13','6');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','10','7');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','10','8');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','15','9');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','15','10');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','15','11');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','12','12');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','16','13');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('false','17','13');
insert into tp_guide_teacher(main,guide_teacher_id,topic_id) values('true','16','14');

-- tp_students-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO tp_students(topic_id, student_id, mid_pass) VALUES
    (1, 41, FALSE)
  , (1, 42, TRUE)
  , (10, 43, TRUE)
  , (12, 50, TRUE)
ON CONFLICT DO NOTHING;

-- tp_topic_review_teachers-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO tp_topic_review_teachers(topic_id, review_teacher_id) VALUES
    (1, 15)
  , (10, 11)
ON CONFLICT DO NOTHING;

-- sc_criterion_1637403518823-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.8059','false','2021-11-20 16:19:38.390783',null,'0','false',null,null,null);
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.809889','false','2021-11-20 16:19:38.392777','<p><strong>A. Mục tiêu và định hướng của luận văn</strong></p>','0','false',null,null,'1');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.812883','false','2021-11-20 16:19:38.397765','<p>A1. <i>Động cơ và mục tiêu của luận văn</i></p><p>a. Sinh viên hoàn toàn không đề cập đến động cơ và mục tiêu của công việc luận văn</p><p>b. Sinh viên có đề cập đến động cơ và mục tiêu của công việc luận văn nhưng chưa rõ ràng</p><p>c. Sinh viên trình bày rõ động cơ và mục tiêu của công việc luận văn nhưng nên điều chỉnh vì quá mục tiêu là hơi khó hoặc còn đơn giản</p><p>d. Sinh viên trình bày động cơ và mục tiêu của công việc luận văn rõ ràng và hợp lý</p>','0','true',null,null,'2');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.815874','false','2021-11-20 16:19:38.398762','<p>A2. <i>Kế hoạch làm việc cho luận văn</i></p><p>a. Hoàn toàn không có kế hoạch làm việc cho giai đoạn LVTN</p><p>b. Có kế hoạch làm việc nhưng chưa còn đơn giản</p><p>c. Có kế hoạch làm việc rõ ràng nhưng chưa thật hợp lý&nbsp;</p><p>d. Có kế hoạch làm việc rõ ràng và hợp lý</p>','1','true',null,null,'2');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.817868','false','2021-11-20 16:19:38.393776','<p><strong>B. Công việc sinh viên tiến hành trong giai đoạn luận văn tốt nghiệp</strong></p>','1','false',null,null,'1');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.820861','false','2021-11-20 16:19:38.399759','<p>B1. <i>Đánh giá, biện luận các phương pháp tiếp cận cho bài toán đặt ra</i></p><p>a. Chỉ đưa ra một hướng tiếp cận và hoàn toàn không biện luận/đánh giá</p><p>b. Đưa ra nhiều hướng tiếp cận và so sánh đánh giá; hoặc chỉ đưa ra một hướng tiếp cận nhưng có biện luận. Tuy nhiên các đánh giá/biện luận chưa thật hợp lý và nên được cải thiện.</p><p>c. Đưa ra nhiều hướng tiếp cận và có so sánh đánh giá; hoặc chỉ đưa ra một hướng tiếp cận nhưng có biện luận. Các đánh giá/biện luận là tương đối hợp lý nhưng vẫn còn một số điểm cần làm rõ.</p><p>d. Đưa ra nhiều hướng tiếp cận và so sánh đánh giá; hoặc chỉ đưa ra một hướng tiếp cận nhưng có biện luận. Các đánh giá/biện luận là rất hợp lý.</p>','0','true',null,null,'5');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.823853','false','2021-11-20 16:19:38.400757','<p>B2. <i>Chất lượng công việc đã tiến hành</i></p><p>a. Những việc sinh viên đã làm là quá đơn giản (thiết kế hệ thống, hiện thực, thử nghiệm)</p><p>b. Công việc tiến hành có độ khó phù hợp (đọc hiểu tài liệu công nghệ, đọc tài liệu học thuật, thiết kế, demo tính năng ban đầu) nhưng tiến độ còn chậm</p><p>c. Công việc có độ khó phù hợp, tiến độ hợp lý</p><p>d. Công việc có độ khó cao, kết quả ban đầu rất tốt.</p>','1','true',null,null,'5');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.827841','false','2021-11-20 16:19:38.394774','<p><strong>C.</strong> <strong>Nhận xét về bản báo cáo đề cương luận văn</strong></p>','2','false',null,null,'1');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.829837','false','2021-11-20 16:19:38.401761','<p>C1. <i>Hình thức trình bày báo cáo</i></p><p>a. Trình bày rất cẩu thả, nhiều lỗi chính tả, không chú ý định dạng&nbsp;</p><p>b. Trình bày có định dạng hợp lý, nhưng còn nhiều lỗi chính tả</p><p>c. Trình bày có định dạng hợp lý, thỉnh thoảng có lỗi chính tả&nbsp;</p><p>d. Trình bày tốt, hầu như không có lỗi chính tả</p>','0','true',null,null,'8');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.832828','false','2021-11-20 16:19:38.402751','<p>C2. <i>Cấu trúc và nội dung báo cáo</i></p><p>a. Báo cáo trình bày lộn xộn, phần lớn là sao chép&nbsp;</p><p>b. Báo cáo trình bày có cấu trúc nhưng chưa thật hợp lý (nội dung do sinh viên tự viết &lt;50%)&nbsp;</p><p>c. Báo cáo có cấu trúc hợp lý, rõ ràng (nội dung do sinh viên viết &gt;=50%)&nbsp; nhưng nên có thêm phụ lục&nbsp;</p><p>d. Báo cáo có cấu trúc hợp lý, rõ ràng, phụ lục đầy đủ</p>','1','true',null,null,'8');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.834822','false','2021-11-20 16:19:38.39577','<p><strong>D. Nhận xét về nội dung thuyết trình của sinh viên</strong></p>','3','false',null,null,'1');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.836817','false','2021-11-20 16:19:38.402751','<p>D1.<i> Nội dung slides trình bày</i></p><p>a. Quá nhiều lỗi trình bày (sơ sài, cẩu thả hoặc quá lạm dụng hiệu ứng)&nbsp;</p><p>b. Nội dung slides được tổ chức hợp lý nhưng thỉnh thoảng vẫn có các lỗi trình bày</p><p>c. Nội dung slides tốt, gần như không có lỗi trình bày&nbsp;</p><p>d. Trình bày nội dung sáng tạo, sinh động, dễ theo dõi</p>','0','true',null,null,'11');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.838813','false','2021-11-20 16:19:38.403749','<p>D2. <i>Trình bày/ trả lời câu hỏi phản biện</i></p><p>a. Gần như không trả lời được các câu hỏi phản biện&nbsp;</p><p>b. Trả lời được các câu hỏi phản biện nhưng không thật sự tự tin và có nhiều điểm chưa rõ ràng</p><p>c. Trả lời được các câu hỏi phản biện dù chưa thật hợp lý&nbsp;</p><p>d. Trình bày tốt, rõ ràng mạch lạc. Trả lời các câu hỏi phản biện tự tin và chính xác</p>','1','true',null,null,'11');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.841804','false','2021-11-20 16:19:38.39677','<p><strong>E. Nhận xét tổng quan</strong></p>','4','false',null,null,'1');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.843799','false','2021-11-20 16:19:38.404746','<p>E1. <i>Đánh giá tổng quan nội dung đề cương luận văn của sinh viên</i></p><p>a. Chưa đạt</p><p>b. Đạt</p><p>c. Tốt</p><p>d. Rất tốt</p>','0','true',null,null,'14');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:17:23.84679','false','2021-11-20 16:19:38.404746','<p>E2. <i>Các góp ý thêm (nếu có)</i></p>','1','true',null,null,'14');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.467984','false','2021-11-20 16:23:07.467984',null,null,null,null,null,null);
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.471998','false','2021-11-20 16:23:07.471998','<p><strong>A. Mục tiêu và định hướng của đề tài</strong></p>','0',null,null,null,'17');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.475963','false','2021-11-20 16:23:07.475963','<p>A1. <i>Động cơ và mục tiêu của đề tài</i></p><p>a. Sinh viên hoàn toàn không đề cập đến động cơ và mục tiêu của đề tài&nbsp;</p><p>b. Sinh viên có đề cập đến động cơ và mục tiêu của đề tài nhưng chưa rõ ràng</p><p>c. Sinh viên trình bày rõ động cơ và mục tiêu của đề tài nhưng nên điều chỉnh vì quá mục tiêu là hơi khó hoặc còn đơn giản</p><p>d. Sinh viên trình bày động cơ và mục tiêu của đề tài rõ ràng và hợp lý</p>','0','true',null,null,'18');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.478955','false','2021-11-20 16:23:07.478955','<p>A2. <i>Kế hoạch làm việc cho luận văn</i></p><p>a. Hoàn toàn không có kế&nbsp; hoạch làm việc cho giai đoạn LVTN</p><p>b. Có kế hoạch làm việc nhưng còn đơn giản</p><p>c. Có kế hoạch làm việc rõ ràng nhưng chưa thật hợp lý&nbsp;</p><p>d. Có kế hoạch làm việc rõ ràng và hợp lý</p>','1','true',null,null,'18');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.48095','false','2021-11-20 16:23:07.48095','<p><strong>B. Công việc sinh viên tiến hành trong giai đoạn đề cương luận văn</strong></p>','1',null,null,null,'17');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.483942','false','2021-11-20 16:23:07.483942','<p>B1. <i>Đánh giá, biện luận các phương pháp tiếp cận cho bài toán đặt ra</i></p><p>a. Chỉ đưa ra một hướng tiếp cận và hoàn toàn không biện luận/đánh giá.</p><p>b. Đưa ra nhiều hướng tiếp cận và so sánh đánh giá; hoặc chỉ đưa ra một hướng tiếp cận nhưng có biện luận. Tuy nhiên các đánh giá/biện luận chưa thật hợp lý và nên được cải thiện.</p><p>c. Đưa ra nhiều hướng tiếp cận và so sánh đánh giá; hoặc chỉ đưa ra một hướng tiếp cận nhưng có biện luận. Các đánh giá/biện luận là tương đối hợp lý nhưng vẫn còn một số điểm cần làm rõ.</p><p>d. Đưa ra nhiều hướng tiếp cận và so sánh đánh giá; hoặc chỉ đưa ra một hướng tiếp cận nhưng có biện luận. Các đánh giá/biện luận là rất hợp lý.</p>','0','true',null,null,'21');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.486964','false','2021-11-20 16:23:07.486964','<p>B2. <i>Chất lượng công việc đã tiến hành</i></p><p>a. Những việc sinh viên đã làm là quá đơn giản (thiết kế giao diện ở mức đơn giản, viết chạy thử các API...)</p><p>b. Công việc tiến hành có độ khó phù hợp (đọc hiểu tài liệu công nghệ, đọc tài liệu học thuật, thiết kế, demo tính năng...) nhưng tiến độ còn chậm</p><p>c. Công việc có độ khó phù hợp, tiến độ&nbsp; hợp lý</p><p>d. Công việc có độ khó cao, kết quả ban đầu rất tốt</p>','1','true',null,null,'21');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.489924','false','2021-11-20 16:23:07.489924','<p><strong>C. Nhận xét về bản báo cáo đề cương luận văn</strong></p>','2',null,null,null,'17');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.492916','false','2021-11-20 16:23:07.492916','<p>C1. <i>Hình thức trình bày báo cáo</i></p><p>a. Trình bày rất cẩu thả, nhiều lỗi chính tả, không chú ý định dạng&nbsp;</p><p>b. Trình bày có định dạng hợp lý, nhưng còn nhiều lỗi chính tả</p><p>c. Trình bày có định dạng hợp lý, thỉnh thoảng có lỗi chính tả&nbsp;</p><p>d. Trình bày tốt, hầu như không có lỗi chính tả</p>','0','true',null,null,'24');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.494911','false','2021-11-20 16:23:07.494911','<p>C2. <i>Cấu trúc và nội dung báo cáo</i></p><p>a. Báo cáo trình bày lộn xộn, phần lớn là sao chép&nbsp;</p><p>b. Báo cáo trình bày có cấu trúc nhưng chưa thật hợp lý (nội dung do sinh viên tự viết &lt;50%)&nbsp;</p><p>c. Báo cáo có cấu trúc hợp lý, rõ ràng (nội dung do sinh viên viết &gt;=50%)&nbsp; nhưng nên có thêm phụ lục&nbsp;</p><p>d. Báo cáo có cấu trúc hợp lý, rõ ràng, phụ lục đầy đủ</p>','1','true',null,null,'24');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.496906','false','2021-11-20 16:23:07.496906','<p><strong>D. Nhận xét về nội dung thuyết trình của sinh viên</strong></p>','3',null,null,null,'17');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.4989','false','2021-11-20 16:23:07.499898','<p>D1.<i> Nội dung slides trình bày</i></p><p>a. Quá nhiều lỗi trình bày (sơ sài, cẩu thả hoặc quá lạm dụng hiệu ứng)&nbsp;</p><p>b. Nội dung slides được tổ chức hợp lý nhưng thỉnh thoảng vẫn có các lỗi trình bày</p><p>c. Nội dung slides tốt, gần như không có lỗi trình bày&nbsp;</p><p>d. Trình bày nội dung sáng tạo, sinh động, dễ theo dõi</p>','0','true',null,null,'27');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.501892','false','2021-11-20 16:23:07.501892','<p>D2. <i>Trình bày/ trả lời câu hỏi phản biện</i></p><p>a. Gần như không trả lời được các câu hỏi phản biện&nbsp;</p><p>b. Trả lời được các câu hỏi phản biện nhưng không thật sự tự tin và có nhiều điểm chưa rõ ràng</p><p>c. Trả lời được các câu hỏi phản biện dù chưa thật hợp lý&nbsp;</p><p>d. Trình bày tốt, rõ ràng mạch lạc. Trả lời các câu hỏi phản biện tự tin và chính xác</p>','1','true',null,null,'27');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.503887','false','2021-11-20 16:23:07.503887','<p><strong>E. Nhận xét tổng quan</strong></p>','4',null,null,null,'17');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.505882','false','2021-11-20 16:23:07.505882','<p>E1. <i>Đánh giá tổng quan nội dung đề cương luận văn của sinh viên</i></p><p>a. Chưa đạt</p><p>b. Đạt</p><p>c. Tốt</p><p>d. Rất tốt</p>','0','true',null,null,'30');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:23:07.508874','false','2021-11-20 16:23:07.508874','<p>E2. <i>Các góp ý thêm (nếu có)</i></p>','1','true',null,null,'30');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.917098','false','2021-11-20 16:42:57.917098',null,null,null,null,null,null);
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.921087','false','2021-11-20 16:42:57.921087','<p><strong>(i). KẾT QUẢ LUẬN VĂN SO VỚI NHIỆM VỤ CỦA ĐỀ TÀI ĐẶT RA </strong><i><strong>(Tối đa 50 điểm)</strong></i></p>','0',null,null,null,'33');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.925076','false','2021-11-20 16:42:57.925076','<p><strong>Câu 1.</strong><i><strong>Đánh giá về kết quả luận văn so với nhiệm vụ của đề tài đặt ra&nbsp;</strong></i></p><ul><li>Kết quả chỉ đáp ứng một phần nhỏ nhiệm vụ của đề tài với khối lượng công việc dưới 50%. <strong>(0≤Điểm&lt;10)</strong></li><li>Kết quả đáp ứng phần nhiệm vụ cơ bản của đề tài đặt ra với khối lượng công việc từ 50% đến 70%. <strong>(10≤Điểm&lt;35)</strong></li><li>Kết quả đáp ứng phần lớn nhiệm vụ chính của đề tài đặt ra với khối lượng công việc từ 70% đến 95%.&nbsp; <strong>(35≤Điểm&lt;47)</strong></li><li>Kết quả đáp ứng đầy đủ nhiệm vụ của đề tài đặt ra với khối lượng công việc trên 95%.&nbsp; <strong>(47≤Điểm≤50)</strong></li></ul>','0','true',null,null,'34');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.92707','false','2021-11-20 16:42:57.92707','<p><strong>(ii). VIỆC THỰC HIỆN ĐÁNH GIÁ KẾT QUẢ LUẬN VĂN CỦA SINH VIÊN (Tối đa 20 điểm)</strong></p>','1',null,null,null,'33');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.930063','false','2021-11-20 16:42:57.930063','<p><strong>Câu 2.</strong><i><strong>Đánh giá việc nhận diện các lợi ích thực tế của giải pháp trong luận văn</strong></i></p><ul><li>Sinh viên không nêu được các lợi ích thực tế của giải pháp trong luận văn. <strong>(0≤Điểm&lt;1)</strong></li><li>Sinh viên nêu được một vài lợi ích thực tế của giải pháp trong luận văn về một vài khía cạnh nhất định nhưng không có minh chứng cụ thể. <strong>(1≤Điểm&lt;3)</strong></li><li>Sinh viên nêu được các lợi ích thực tế của giải pháp trong luận văn về một vài khía cạnh nhất định và có kèm theo minh chứng cụ thể. <strong>(3≤Điểm≤4)</strong></li><li>Sinh viên nêu được các lợi ích thực tế của giải pháp trong luận văn về các khía cạnh một cách toàn diện và có kèm theo đầy đủ các minh chứng cụ thể.&nbsp;<strong>(4&lt;Điểm≤5)</strong></li></ul>','0','true',null,null,'36');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.932057','false','2021-11-20 16:42:57.932057','<p><strong>Câu 3.</strong><i><strong>Đánh giá về những giải pháp được đề xuất trong luận văn để giải quyết vấn đề</strong></i></p><ul><li>Sinh viên nêu ra một giải pháp nhưng không biết ưu và nhược điểm. <strong>(0≤Điểm&lt;1)</strong></li><li>Sinh viên nêu ra một giải pháp, và phân tích ưu và nhược điểm. <strong>(1≤Điểm&lt;3)</strong></li><li>Sinh viên đưa ra nhiều giải pháp để giải quyết bài toán, có biện luận lựa chọn giải pháp nhưng chưa hợp lý. <strong>(3≤Điểm≤4)</strong></li><li>Sinh viên đưa ra nhiều giải pháp để giải quyết bài toán, đồng thời nêu rõ lý do và biện luận đúng, rõ ràng tại sao lựa chọn giải pháp đó. <strong>(4&lt;Điểm≤5)</strong></li></ul>','1','true',null,null,'36');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.935049','false','2021-11-20 16:42:57.935049','<p><strong>Câu 4.</strong><i><strong>Đánh giá sản phẩm đạt được (mô hình, chương trình, hệ thống, …)&nbsp;</strong></i></p><ul><li>Sinh viên không thực hiện đánh giá sản phẩm đạt được của đề tài. <strong>(0≤Điểm&lt;1)</strong></li><li>Sinh viên trình bày phần đánh giá cho đề tài nhưng chưa đánh giá cho sản phẩm đạt được của đề tài. <strong>(1≤Điểm&lt;5)</strong></li><li>Sinh viên đánh giá sản phẩm đạt được của đề tài, ví dụ: bằng kỹ thuật kiểm tra chuyên môn hoặc làm thí nghiệm, nhưng không phù hợp với các yêu cầu của đề tài. <strong>(5≤Điểm&lt;9)</strong></li><li>Sinh viên đánh giá sản phẩm đạt được của đề tài, ví dụ: bằng kỹ thuật kiểm tra chuyên môn hoặc làm thí nghiệm, phù hợp với các yêu cầu của đề tài. <strong>(9≤Điểm≤10)</strong></li></ul>','2','true',null,null,'36');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.938043','false','2021-11-20 16:42:57.938043','<p><strong>(iii). BÀI BÁO CÁO LUẬN VĂN TỐT NGHIỆP (Tối đa 15 điểm)</strong></p>','2',null,null,null,'33');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.941033','false','2021-11-20 16:42:57.941033','<p><strong>Câu 5. </strong><i><strong>Khả năng viết báo cáo</strong></i></p><ul><li>Báo cáo được trình bày không có cấu trúc.<strong> (0≤Điểm&lt;1)</strong></li><li>Báo cáo được trình bày có cấu trúc nhưng rời rạc trong việc kết nối các ý tưởng.<strong> (1≤Điểm&lt;5)</strong></li><li>Báo cáo được trình bày có cấu trúc, các ý tưởng được kết nối mạch lạc, tuy nhiên chưa thể hiện chi tiết quá trình thực hiện đề tài và kết quả đạt được của đề tài, có nhiều lỗi về hình thức trình bày như chính tả, ngữ pháp, hình ảnh, và bảng biểu. <strong>(5≤Điểm&lt;9)</strong></li><li>Báo cáo được trình bày có cấu trúc rõ ràng và có nội dung chi tiết về quá trình thực hiện đề tài và kết quả đạt được của đề tài, các ý tưởng được kết nối mạch lạc, không có (hoặc rất ít) lỗi về hình thức trình bày.<strong> (9≤Điểm≤10)</strong></li></ul>','0','true',null,null,'40');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.944026','false','2021-11-20 16:42:57.944026','<p><strong>Câu 6.</strong><i><strong>Việc sử dụng tài liệu tham khảo&nbsp;</strong></i></p><ul><li>Quá ít tài liệu tham khảo (&lt; 5 tài liệu) được sử dụng trong luận văn. <strong>(0≤Điểm&lt;1)</strong></li><li>Tài liệu tham khảo được sử dụng phù hợp trong luận văn, nhưng chưa được trình bày chi tiết và chưa có phần trích dẫn tài liệu. <strong>(1≤Điểm&lt;3)</strong></li><li>Tài liệu tham khảo được sử dụng phù hợp trong luận văn, được trình bày chi tiết, nhưng có phần trích dẫn tài liệu chưa đầy đủ. <strong>(3≤Điểm≤4)</strong></li><li>Tài liệu tham khảo được sử dụng phù hợp trong luận văn, được trình bày chi tiết và có phần trích dẫn tài liệu đầy đủ và rõ ràng. <strong>(4&lt;Điểm≤5)</strong></li></ul>','1','true',null,null,'40');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.946791','false','2021-11-20 16:42:57.946791','<p><strong>(iv). KỸ NĂNG VÀ THÁI ĐỘ THỰC HIỆN LUẬN VĂN CỦA SINH VIÊN (Tối đa 15 điểm)</strong></p>','3',null,null,null,'33');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.94978','false','2021-11-20 16:42:57.94978','<p><strong>Câu 7.</strong><i><strong>Nội dung slide thuyết trình của sinh viên</strong></i></p><ul><li>Nội dung slide sơ sài, rời rạc, không rõ ràng. (<strong>0≤Điểm&lt;1)</strong></li><li>Nội dung slide được tổ chức hợp lý nhưng có một số lỗi trình bày về chính tả, hình ảnh và bảng biểu được sử dụng minh họa không phù hợp. <strong>(1≤Điểm&lt;3)</strong></li><li>Nội dụng slide tốt, được tổ chức hợp lý, có hình ảnh và bảng biểu minh họa phù hợp nhưng chưa sinh động. <strong>(3≤Điểm≤4)</strong></li><li>Nội dung slide tốt, được tổ chức hợp lý, có hình ảnh và bảng biểu minh họa sáng tạo và sinh động. <strong>(4&lt;Điểm≤5)</strong></li></ul>','0','true',null,null,'43');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.951774','false','2021-11-20 16:42:57.951774','<p><strong>Câu 8.</strong><i><strong>Khả năng thuyết trình của sinh viên</strong></i></p><ul><li>Sinh viên trình bày không đầy đủ nội dung, không rõ ràng, gây khó hiểu. <strong>(0≤Điểm&lt;1)</strong></li><li>Sinh viên trình bày đầy đủ nội dung, nhưng thiếu tự tin, không thu hút, thường bị ngắt quãng. <strong>(1≤Điểm&lt;3)</strong></li><li>Sinh viên trình bày đầy đủ nội dung, tự tin, ít ngắt quãng, nhưng không quản lý tốt quỹ thời gian. <strong>(3≤Điểm≤4)</strong></li><li>Sinh viên trình bày đầy đủ nội dung, tự tin, cuốn hút người nghe, và quản lý tốt quỹ thời gian. <strong>(4&lt;Điểm≤5)</strong></li></ul>','1','true',null,null,'43');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.953769','false','2021-11-20 16:42:57.953769','<p><strong>Câu 9.</strong><i><strong>Đánh giá về việc tổ chức/ chuẩn bị cho nhiệm vụ được giao</strong></i></p><ul><li>Sinh viên không có tổ chức/ chuẩn bị cho nhiệm vụ được giao, không quan tâm đến nhiệm vụ, không có để dành thời gian và công sức để hoàn thành nhiệm vụ. <strong>(0≤Điểm&lt;1)</strong></li><li>Sinh viên thực hiện tổ chức/ chuẩn bị qua loa cho nhiệm vụ được giao, dành ít thời gian và công sức để hoàn thành nhiệm vụ. <strong>(1≤Điểm&lt;3)</strong></li><li>Sinh viên thực hiện tổ chức/ chuẩn bị vừa phải cho nhiệm vụ được giao, dành vừa đủ thời gian và công sức để hoàn thành nhiệm vụ. <strong>(3≤Điểm≤4)</strong></li><li>Sinh viên thực hiện tổ chức/ chuẩn bị tốt cho nhiệm vụ được giao, dành nhiều thời gian và công sức để hoàn thành tốt nhiệm vụ. <strong>(4&lt;Điểm≤5)</strong></li></ul>','2','true',null,null,'43');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 16:42:57.956762','false','2021-11-20 16:42:57.956762','<p><strong>Điểm thưởng</strong><i>(nếu có, ghi rõ lý do của điểm thưởng, ví dụ đối với các đề tài có kết quả xuất sắc, có bài báo khoa học và/hoặc đạt các giải thưởng về học thuật) (Tối đa 10 điểm)</i></p>','4','true',null,null,'33');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:06:17.119671','false','2021-11-20 17:06:17.119671',null,null,null,null,null,null);
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:06:17.122664','false','2021-11-20 17:06:17.122664','<p><strong>Câu 1. </strong><i><strong>Khả năng định nghĩa yêu cầu của vấn đề (bài toán) trong đề tài</strong></i></p><p>&nbsp; &nbsp; &nbsp; &nbsp; A. Sinh viên không thể xác định các yêu cầu của vấn đề được cho.</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;B. Sinh viên có thể phát biểu và giải thích 1-3 yêu cầu của vấn đề được cho, nhưng thiếu ràng buộc, chưa rõ ràng, không thể kiểm tra được, hay phụ thuộc hiện thực.</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;C. Sinh viên có thể phát biểu và giải thích tất cả các yêu cầu của vấn đề được cho; trong đó, một số yêu cầu có thể kiểm tra được và một số yêu cầu thiếu ràng buộc hoặc phụ thuộc hiện thực.</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; D. Sinh viên có thể phát biểu và giải thích tất cả các yêu cầu của vấn đề được cho với các ràng buộc rõ ràng, có thể kiểm tra được, và không phụ thuộc hiện thực.</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;E. Không đánh giá được vì:&nbsp;</p>','0','true',null,null,'48');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:06:17.126653','false','2021-11-20 17:06:17.126653','<p><strong>Câu 2. </strong><i><strong>Khả năng phân tích vấn đề cho bài toán trong đề tài</strong></i></p><p>&nbsp; &nbsp; &nbsp; &nbsp; A. Sinh viên không thể xác định các thành phần chính của vấn đề.</p><p>&nbsp; &nbsp; &nbsp; &nbsp; B. Sinh viên có thể nhận diện một số thành phần chính nhưng chưa đúng hoặc không thể kiểm tra liệu có phù hợp cho vấn đề.</p><p>&nbsp; &nbsp; &nbsp; &nbsp; C. Sinh viên có thể nhận diện một số thành phần chính và thể hiện được sự liên hệ giữa các thành phần của vấn đề.</p><p>&nbsp; &nbsp; &nbsp; &nbsp; D. Sinh viên có thể nhận diện tất cả thành phần và các mối liên hệ giữa các thành phần này cho vấn đề, tạo cơ sở để hình thành giải pháp cho vấn đề.</p><p>&nbsp; &nbsp; &nbsp; &nbsp; E. Không đánh giá được vì:&nbsp;</p>','1','true',null,null,'48');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:06:17.129645','false','2021-11-20 17:06:17.129645','<p><strong>Câu 3.</strong><i><strong> Khả năng thiết kế hệ thống (một phần hệ thống như kiến trúc phần mềm, cơ sở dữ liệu, mạng, …) dựa trên yêu cầu bài toán</strong></i></p><p>&nbsp; &nbsp; &nbsp; &nbsp; A. Sinh viên không thể thiết kế hệ thống để đáp ứng yêu cầu bài toán.</p><p>&nbsp; &nbsp; &nbsp; &nbsp; B. Sinh viên có thể thiết kế hệ thống nhưng cần nhiều hướng dẫn chi tiết để đáp ứng yêu cầu bài toán.</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;C. Sinh viên có thể thiết kế hệ thống nhưng vẫn cần hướng dẫn để đáp ứng yêu cầu bài toán.&nbsp;</p><p>&nbsp; &nbsp; &nbsp; &nbsp; D. Sinh viên có thể tự thiết kế hệ thống phù hợp để đáp ứng yêu cầu bài toán.&nbsp;</p><p>&nbsp; &nbsp; &nbsp; &nbsp; E. Không đánh giá được vì:&nbsp;</p>','2','true',null,null,'48');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:06:17.133635','false','2021-11-20 17:06:17.133635','<p><strong>Câu 4.</strong><i><strong> Khả năng sử dụng các nguyên lý phát triển phần mềm, các quy trình phát triển phần mềm, và/ hoặc các công cụ thiết kế phần mềm, … để mô hình hóa hệ thống phần mềm trong đề tài luận văn</strong></i></p><p>&nbsp; &nbsp; &nbsp; &nbsp;A. Sinh viên không thể áp dụng các nguyên lý phát triển phần mềm, các quy trình phát triển phần mềm, và/ hoặc các công cụ thiết kế phần mềm, …để mô hình hóa hệ thống phần mềm trong đề tài luận văn.</p><p>&nbsp; &nbsp; &nbsp; &nbsp;B. Sinh viên có thể áp dụng các nguyên lý phát triển phần mềm, các quy trình phát triển phần mềm, và/ hoặc các công cụ thiết kế phần mềm, …; nhưng giới hạn cho 1-2 khía cạnh (<i>view</i>) của hệ thống phần mềm như dữ liệu và/ hoặc chức năng&nbsp;của hệ thống phần mềm. Phần mô hình hóa chưa liên hệ được với các yêu cầu của hệ thống phần mềm.</p><p>&nbsp; &nbsp; &nbsp; &nbsp;C. Sinh viên có thể áp dụng các nguyên lý phát triển phần mềm, các quy trình phát triển phần mềm, và/ hoặc các công cụ thiết kế phần mềm, …; nhưng giới hạn cho 3-4 khía cạnh (<i>view</i>) của hệ thống phần mềm. Phần mô hình hóa liên hệ được với một số yêu cầu của hệ thống phần mềm.</p><p>&nbsp; &nbsp; &nbsp; &nbsp;D. Sinh viên có thể áp dụng các nguyên lý phát triển phần mềm, các quy trình phát triển phần mềm, và/ hoặc các công cụ thiết kế phần mềm, … để mô hình hóa đầy đủ hệ thống phần mềm và liên hệ được đầy đủ các yêu cầu của hệ thống phần mềm.</p><p>&nbsp; &nbsp; &nbsp; &nbsp;E. Không đánh giá được vì:&nbsp;</p>','3','true',null,null,'48');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:09:28.932708','false','2021-11-20 17:09:28.932708',null,null,null,null,null,null);
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:09:28.935701','false','2021-11-20 17:09:28.935701','<p><strong>(i). KẾT QUẢ LUẬN VĂN SO VỚI NHIỆM VỤ CỦA ĐỀ TÀI ĐẶT RA (tối đa 50 điểm)</strong></p>','0',null,null,null,'53');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:09:28.939691','false','2021-11-20 17:09:28.939691','<p><strong>Câu 1.</strong> <i><strong>Đánh giá về kết quả luận văn so với nhiệm vụ của đề tài đặt ra&nbsp;</strong></i></p><ul><li>Kết quả chỉ đáp ứng một phần nhỏ nhiệm vụ của đề tài với khối lượng công việc dưới 50%. <strong>(0≤Điểm&lt;10)</strong></li><li>Kết quả đáp ứng phần nhiệm vụ cơ bản của đề tài đặt ra với khối lượng công việc từ 50% đến 70%. <strong>(10≤Điểm&lt;35)</strong></li><li>Kết quả đáp ứng phần lớn nhiệm vụ chính của đề tài đặt ra với khối lượng công việc từ 70% đến 95%.&nbsp; <strong>(35≤Điểm&lt;47)</strong></li><li>Kết quả đáp ứng đầy đủ nhiệm vụ của đề tài đặt ra với khối lượng công việc trên 95%.&nbsp; <strong>(47≤Điểm≤50)</strong></li></ul>','0','true',null,null,'54');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:13:40.604898','false','2021-11-20 17:13:53.517933',null,'0','false',null,null,null);
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:13:40.607925','false','2021-11-20 17:13:53.519928','<p><strong>I. ĐÁNH GIÁ KẾT QUẢ LUẬN VĂN (tối đa 50 điểm)</strong></p>','0','false',null,null,'56');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:13:40.610882','false','2021-11-20 17:13:53.520926','<p><strong>Câu 1.</strong> <i><strong>Đánh giá kết quả luận văn so với nhiệm vụ của đề tài đặt ra&nbsp;</strong></i></p><ul><li>Kết quả chỉ đáp ứng một phần nhỏ nhiệm vụ của đề tài với khối lượng công việc dưới 50%. <strong>(0≤Điểm&lt;10)</strong></li><li>Kết quả đáp ứng phần nhiệm vụ cơ bản của đề tài đặt ra với khối lượng công việc từ 50% đến 70%. <strong>(10≤Điểm&lt;35)</strong></li><li>Kết quả đáp ứng phần lớn nhiệm vụ chính của đề tài đặt ra với khối lượng công việc từ 70% đến 95%.&nbsp; <strong>(35≤Điểm&lt;47)</strong></li><li>Kết quả đáp ứng đầy đủ nhiệm vụ của đề tài đặt ra với khối lượng công việc trên 95%.&nbsp; <strong>(47≤Điểm≤50)</strong></li></ul>','0','true',null,null,'57');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:13:40.613878','false','2021-11-20 17:13:53.521927','<p><strong>Câu 2.</strong> <i><strong>Đánh giá việc nhận diện các lợi ích thực tế của giải pháp trong luận văn</strong></i></p><ul><li>Sinh viên không nêu được các lợi ích thực tế của giải pháp trong luận văn. <strong>(0≤Điểm&lt;1)</strong></li><li>Sinh viên nêu được một vài lợi ích thực tế của giải pháp trong luận văn về một vài khía cạnh nhất định nhưng không có minh chứng cụ thể. <strong>(1≤Điểm&lt;3)</strong></li><li>Sinh viên nêu được các lợi ích thực tế của giải pháp trong luận văn về một vài khía cạnh nhất định và có kèm theo minh chứng cụ thể. <strong>(3≤Điểm≤4)</strong></li><li>Sinh viên nêu được các lợi ích thực tế của giải pháp trong luận văn về các khía cạnh một cách toàn diện và có kèm theo đầy đủ các minh chứng cụ thể.&nbsp;<strong>(4&lt;Điểm≤5)</strong></li></ul>','1','true',null,null,'57');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:13:40.617864','false','2021-11-20 17:13:53.52292','<p><strong>Câu 3.</strong> <i><strong>Đánh giá về những giải pháp được đề xuất trong luận văn để giải quyết vấn đề</strong></i></p><ul><li>Sinh viên nêu ra một giải pháp nhưng không biết ưu và nhược điểm. <strong>(0≤Điểm&lt;1)</strong></li><li>Sinh viên nêu ra một giải pháp, và phân tích ưu và nhược điểm. <strong>(1≤Điểm&lt;3)</strong></li><li>Sinh viên đưa ra nhiều giải pháp để giải quyết bài toán, có biện luận lựa chọn giải pháp nhưng chưa hợp lý. <strong>(3≤Điểm≤4)</strong></li><li>Sinh viên đưa ra nhiều giải pháp để giải quyết bài toán, đồng thời nêu rõ lý do và biện luận đúng, rõ ràng tại sao lựa chọn giải pháp đó.&nbsp;<strong>(4&lt;Điểm≤5)</strong></li></ul>','2','true',null,null,'57');
insert into sc_criterion(created_at,deleted,updated_at,description,display_order,mark,max_score,min_score,parent_id) values('2021-11-20 17:13:40.620855','false','2021-11-20 17:13:53.523917','<p><strong>Câu 4.</strong> <i><strong>Đánh giá sản phẩm đạt được của đề tài (mô hình, chương trình, hệ thống, …)&nbsp;</strong></i></p><ul><li>Sinh viên không thực hiện đánh giá sản phẩm đạt được của đề tài. <strong>(0≤Điểm&lt;1)</strong></li><li>Sinh viên trình bày phần đánh giá cho đề tài nhưng chưa đánh giá cho sản phẩm đạt được của đề tài. <strong>(1≤Điểm&lt;5)</strong></li><li>Sinh viên đánh giá sản phẩm đạt được của đề tài, ví dụ: bằng kỹ thuật kiểm tra chuyên môn hoặc làm thí nghiệm, nhưng không phù hợp với các yêu cầu của đề tài. <strong>(5≤Điểm&lt;9)</strong></li><li>Sinh viên đánh giá sản phẩm đạt được của đề tài, ví dụ: bằng kỹ thuật kiểm tra chuyên môn hoặc làm thí nghiệm, phù hợp với các yêu cầu của đề tài. <strong>(9≤Điểm≤10)</strong></li></ul>','3','true',null,null,'57');

-- sc_template_1637406178872-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into sc_template(created_at,deleted,updated_at,description,guide_teacher,mid_semester,name,number_mark,review_teacher,thesis,root_criterion_id) values('2021-11-20 18:01:07.034785','false','2021-11-20 18:01:07.034785','Người đánh giá đánh dấu vào đánh giá phù hợp nhất','true','false','Nhận xét Đề Cương Luận Văn','false','true','false','1');
insert into sc_template(created_at,deleted,updated_at,description,guide_teacher,mid_semester,name,number_mark,review_teacher,thesis,root_criterion_id) values('2021-11-20 18:01:07.035394','false','2021-11-20 18:01:07.035394','người đánh giá đánh dấu vào lựa chọn đánh giá phù hợp ','true','false','Mau_phieuNX_decuong_LVTN_CS','false','true','false','17');
insert into sc_template(created_at,deleted,updated_at,description,guide_teacher,mid_semester,name,number_mark,review_teacher,thesis,root_criterion_id) values('2021-11-20 18:01:07.035632','false','2021-11-20 18:01:07.035632','I. PHẦN ĐÁNH GIÁ ĐIỂM CỦA SINH VIÊN THỰC HIỆN LUẬN VĂN TỐT NGHIỆP

Hướng dẫn đánh giá: cho mỗi Tiêu chí đánh giá, Thầy/ Cô cho điểm đánh giá ở cột Điểm đánh giá tương ứng với lựa chọn A, B, C, hoặc D nhằm phản ánh kết quả luận văn cũng như năng lực và thái độ của sinh viên ngay sau khi thực hiện luận văn tốt nghiệp. ','true','false','Phieu Danh Gia LVTN GVHD phần 1','true','false','true','33');
insert into sc_template(created_at,deleted,updated_at,description,guide_teacher,mid_semester,name,number_mark,review_teacher,thesis,root_criterion_id) values('2021-11-20 18:01:07.035873','false','2021-11-20 18:01:07.035873','<p><strong>II. PHẦN ĐÁNH GIÁ DÀNH CHO CHUẨN ĐẦU RA CỦA CHƯƠNG TRÌNH</strong></p><p>Việc đánh giá ở phần này chỉ dùng để đo lường chất lượng của việc giảng dạy-học tập mà không làm ảnh hưởng đến kết quả đánh giá học tập của các sinh viên được đánh giá.</p><p>Hướng dẫn đánh giá: Thầy/ Cô điền A, B, C hoặc D vào cột&nbsp;<strong>Điểm đánh giá</strong> hoặc khoanh lựa chọn ở cột <strong>Tiêu chí đánh giá</strong> tương ứng để đánh giá hoặc nêu lý do ở lựa chọn E trong trường hợp không đánh giá được.&nbsp;</p>','true','false','Phieu Danh Gia LVTN Nganh CS _GVHD phần 2','false','false','true','48');
insert into sc_template(created_at,deleted,updated_at,description,guide_teacher,mid_semester,name,number_mark,review_teacher,thesis,root_criterion_id) values('2021-11-20 18:01:07.036114','false','2021-11-20 18:01:07.036114','<p><strong>PHẦN ĐÁNH GIÁ ĐIỂM CỦA SINH VIÊN THỰC HIỆN LUẬN VĂN TỐT NGHIỆP</strong></p><p>Hướng dẫn đánh giá: cho mỗi&nbsp;<strong>Tiêu chí đánh giá</strong>, Thầy/ Cô cho điểm đánh giá ở cột&nbsp;<strong>Điểm đánh giá</strong> tương ứng với lựa chọn A, B, C, hoặc D nhằm phản ánh kết quả luận văn cũng như năng lực và thái độ của sinh viên ngay sau khi thực hiện luận văn tốt nghiệp.</p>','false','false','Phieu Danh Gia LVTN Nganh CS _GVPB phần 1','true','true','true','53');
insert into sc_template(created_at,deleted,updated_at,description,guide_teacher,mid_semester,name,number_mark,review_teacher,thesis,root_criterion_id) values('2021-11-20 18:01:07.036348','false','2021-11-20 18:01:07.036348',null,'false','false','Phieu Danh Gia LVTN hội đồng','true','false','true','56');
-- insert into sc_template(created_at,deleted,updated_at,description,guide_teacher,mid_semester,name,number_mark,review_teacher,thesis,root_criterion_id) values('2021-11-20 18:01:45.389948','false','2021-11-20 18:01:45.390945','<p>Cán bộ chấm đạt hoặc không đạt</p>','true','true','Phiếu chấm điểm đề cương giữa kỳ','false','false','false',null);
-- insert into sc_template(created_at,deleted,updated_at,description,guide_teacher,mid_semester,name,number_mark,review_teacher,thesis,root_criterion_id) values('2021-11-20 18:02:17.369305','false','2021-11-20 18:02:17.369305','<p>Cán bộ chấm đạt hoặc không đạt</p>','true','true','Phiếu chấm điểm luận văn giữa kỳ','false','false','true',null);

-- sc_template_council_roles_1637403278396-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into sc_template_council_roles(template_id,council_role_id) values('6','1');
insert into sc_template_council_roles(template_id,council_role_id) values('6','2');
insert into sc_template_council_roles(template_id,council_role_id) values('6','3');

-- sc_template_majors_1637406199824-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into sc_template_majors(template_id,major_id) values('1','2');
insert into sc_template_majors(template_id,major_id) values('2','1');
insert into sc_template_majors(template_id,major_id) values('3','1');
insert into sc_template_majors(template_id,major_id) values('3','2');
insert into sc_template_majors(template_id,major_id) values('4','1');
insert into sc_template_majors(template_id,major_id) values('4','2');
insert into sc_template_majors(template_id,major_id) values('5','1');
insert into sc_template_majors(template_id,major_id) values('5','2');
insert into sc_template_majors(template_id,major_id) values('6','1');
insert into sc_template_majors(template_id,major_id) values('6','2');
-- insert into sc_template_majors(template_id,major_id) values('7','1');
-- insert into sc_template_majors(template_id,major_id) values('7','2');
-- insert into sc_template_majors(template_id,major_id) values('8','2');
-- insert into sc_template_majors(template_id,major_id) values('8','1');

-- sc_score_1637661754080-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into sc_score(created_at,deleted,updated_at,comment,score,criterion_id,student_id,teacher_id,template_id,topic_id) values('2021-11-23 16:56:14.851376','false','2021-11-23 16:56:14.851376',null,'A','3','41','6','1','1');
insert into sc_score(created_at,deleted,updated_at,comment,score,criterion_id,student_id,teacher_id,template_id,topic_id) values('2021-11-23 16:57:10.674332','false','2021-11-23 16:57:10.674332',null,'B','3','41','11','1','1');
insert into sc_score(created_at,deleted,updated_at,comment,score,criterion_id,student_id,teacher_id,template_id,topic_id) values('2021-11-23 17:01:40.018786','false','2021-11-23 17:01:40.018786',null,'D','3','41','15','1','1');

-- us_notification-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO us_notification (receiver_id, message) VALUES
    (1, 'Tin nhắn thử nghiệm 1 <a href="https://datai-thesis-web.herokuapp.com/users/1">link</a>')
  , (1, 'Tin nhắn thử nghiệm 2 <a href="https://datai-thesis-web.herokuapp.com/semesters">link</a>')
  , (1, 'Tin nhắn thử nghiệm 3 <a href="https://datai-thesis-web.herokuapp.com/topics">link</a>')
ON CONFLICT DO NOTHING;
INSERT INTO us_notification (receiver_id, message)
  SELECT id, 'Kết thúc thời gian đăng ký đề tài. <a target="_blank" href="http://localhost:3000/my/topics">chi tiết</a>'
  FROM us_user;

