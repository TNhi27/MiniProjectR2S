# TỔNG QUAN DỰ ÁN
## Tổng quan
Ứng dụng tổ chức theo mô hịnh server-client,Được chia thành 2 project nhỏ khác nhau cho backend và frontend.Các RESTApi được viết ở backend được sử dụng cho frontend nhận và sử lý render ra giao diện ngườI dùng.

![alt](https://raw.githubusercontent.com/TNhi27/MiniProjectR2S/main/Document/ktud.jpg)

## Backend
* Sừ dụng Spring boot + Spring security
* Spring JPA để kết nốI csdl
* Hệ quản trị csdl sử dụng MYSQL
* Sử dụng cơ chế xác thực JWT
* Tích hơp SWAGGRER 2 để tạo document cho restful api
## FRONTEND
* Sử dụng REACTJS để xây dựng giao diện
* React router để chuyển trang
* GọI API bằng thư viện hỗ trợ axios.
## CÁC THÀNH PHẦN TRONG CẤU TRÚC
### Các RESTController :
Controller thuộc một phần trong mẫu thiết kế . Controller là đối tượng chịu trách nhiệm xứ lý các yêu cầu và gửi phản hồi . Đặc biệt, sau khi tiếp nhận các yêu cầu điều khiển từ ứng dụng, controllers sẽ phân tích thông tin yêu cầu được gửi đến, gửi dữ liệu qua DAO  để xử lý và gửi phản hồi.dướI dạng ResponseEntity.

### Các lớp DAO: 
  Là đối tượng đại diện cho phần dữ liệu, phương thức xử lý và nghiệp vụ logic.
### JWTService:
Cung cấp các phương thức để tạo, xác thực jwt.
### Filter:
Các lớp cấu hình để lọc các request nhầm phục vụ bảo mật và tốI ưu hệ thống.
### Entity:
Các lớp đạI diện cho các table trong mysql,thể hiện các mốI quan hệ vớI nhau.
### ExceptionHandler:
Nơi sử lý các ngoạI lệ, nhầm đưa ra thông báo cho ngườI dùng.
### Các DTO:
Đóng góI thông tin để dễ dàng trao đổI giữa client và server, hạn chế gửI nhìu request.và còn để bảo mật thông tin.
### View:
Gồm các thành phần bên trong thư mục frontent 
* page: chứa các file render giao diện, và sử lý logic hiện thị bẳng jsx.
* service: các module này dùng để thao tác vớI api
## BẢO MẬT
* Xác nhận đăng nhập bằng JWT được đính kèm trong headers  của request.
* ChuốI jwt có được sau khi đăng nhập thành công.
* ChuốI JWT được xác thực ở tầng filter trước khi đến controlller để xử lý.
* ThờI gian hiệu lực là 1 ngày
* Các url /login","/dangxuat","/upload/**","/v2/** truy cập không cần xác thực.
* Đã disable CROS 
## CÔNG CỤ PHÁT TRIỂN:
	
* SPRING TOOL 4
* MY SQL 
* VS CODE
* POSTMAN
* SWAGGER2 
## KIỂM THỬ 
Kiểm thử unit test
[File Testcase](https://github.com/TNhi27/MiniProjectR2S/blob/main/Document/TestCase.xlsx)

## YÊU CẦU
1. JAVA 11 hoặc cao hơn
2. My SQL
3. NodeJS 

## HƯỚNG DẪN RUN
1. Git clone https://github.com/TNhi27/MiniProjectR2S.git name-project
2. Chạy script MySQL bằng file projectr2s.sql trong thư mục Document
3. Run Backend : Run main class MiniprojectApplication.java
4. Run Frontend: cd frontend > npm start.
5. Login với username = admin và password = 123 để test.
