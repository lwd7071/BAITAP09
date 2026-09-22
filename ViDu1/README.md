# ViDu1 - Spring Boot login và quản lý shop

Project này độc lập với `ViDu2` và `ViDu3`. Ứng dụng dùng Java 21, Spring Boot 4.1.1, Spring Security, Thymeleaf fragment (không dùng Layout Dialect), MapStruct và SQL Server.

## Chạy nhanh

1. Cài JDK 21, Maven và SQL Server.
2. Chạy `database/create-database.sql` trong SQL Server.
3. Sao chép `.env.example` thành `.env`, điền thông tin database. SMTP là bắt buộc khi thử đăng ký hoặc quên mật khẩu; Cloudinary là tùy chọn, nếu bỏ trống ảnh sản phẩm được lưu dưới dạng URL rỗng.
4. Chạy `mvn spring-boot:run` rồi mở `http://localhost:8088`.

Lần chạy đầu tự tạo role `USER`, `ADMIN`, tài khoản admin, category và một số sản phẩm. Tài khoản admin lấy từ `ADMIN_EMAIL`/`ADMIN_PASSWORD` trong `.env`.

## Chức năng

- Đăng ký, gửi lại và xác nhận OTP qua email.
- Đăng nhập bằng email, đăng xuất, quên mật khẩu và đặt lại mật khẩu bằng OTP.
- Dashboard thống kê user, category và product.
- Admin quản lý user, category, product; có tìm kiếm và phân trang.
- User xem sản phẩm của mình và tạo sản phẩm. Quan hệ User 1-n Product.
- Upload ảnh qua Cloudinary khi đã cấu hình đủ ba biến Cloudinary.

## Kiểm thử

`mvn test` dùng H2 và mock email, không cần SQL Server, SMTP hoặc Cloudinary.
