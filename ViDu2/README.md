# ViDu2 - Custom login username/email

Project độc lập với `ViDu1` và `ViDu3`. Dùng Java 21, Spring Boot 4.1.1, Spring Security, MapStruct, Thymeleaf Layout Dialect và SQL Server.

1. Cài JDK 21, Maven, SQL Server.
2. Chạy `database/create-database.sql`.
3. Sao chép `.env.example` thành `.env` và điền database. SMTP dùng cho OTP; Cloudinary dùng cho ảnh.
4. Chạy `mvn spring-boot:run`, mở `http://localhost:8080`.

Tài khoản mẫu lấy từ `ADMIN_USERNAME`, `ADMIN_EMAIL`, `ADMIN_PASSWORD`. Ứng dụng hỗ trợ đăng ký username/email, OTP, quên mật khẩu, login bằng username hoặc email, dashboard, users, categories, products, tìm kiếm, phân trang và upload ảnh.

`mvn test` chạy bằng H2.
