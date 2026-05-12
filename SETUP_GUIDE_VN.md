# Hướng dẫn Cấu hình và Chạy Dự án trên VS Code

Tài liệu này hướng dẫn bạn cách thiết lập môi trường và chạy dự án FMS (Backend & Frontend) sử dụng Visual Studio Code.

## 1. Yêu cầu Hệ thống

Trước khi bắt đầu, hãy đảm bảo máy tính của bạn đã cài đặt các công cụ sau:
- **Java JDK 17**: [Tải xuống JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- **Node.js (v18 trở lên)**: [Tải xuống Node.js](https://nodejs.org/)
- **Docker & Docker Compose**: [Tải xuống Docker Desktop](https://www.docker.com/products/docker-desktop/)
- **Git**: [Tải xuống Git](https://git-scm.com/)

## 2. Các VS Code Extensions Khuyên Dùng

Mở VS Code và cài đặt các extension sau để hỗ trợ phát triển tốt nhất:
- **Extension Pack for Java**: Hỗ trợ lập trình Java.
- **Spring Boot Extension Pack**: Hỗ trợ các tính năng của Spring Boot.
- **ESLint**: Kiểm tra lỗi mã nguồn JavaScript/TypeScript.
- **Prettier**: Định dạng mã nguồn tự động.
- **Docker**: Quản lý các container dễ dàng hơn.

---

## 3. Cấu hình và Chạy Backend

### Bước 1: Mở thư mục backend
Mở VS Code và chọn thư mục `fms_backend`.

### Bước 2: Cấu hình biến môi trường
1. Tìm file `.env.example` trong thư mục `fms_backend`.
2. Sao chép và đổi tên thành `.env`.
3. Mở file `.env` và điền các giá trị phù hợp (ví dụ: `POSTGRES_DB=fms_db`, `POSTGRES_USER=postgres`, `POSTGRES_PASSWORD=123456789`).

### Bước 3: Chạy Cơ sở dữ liệu bằng Docker
Mở terminal trong VS Code (Ctrl + `) và chạy lệnh:
```bash
cd fms_backend
docker-compose up -d
```
Lệnh này sẽ khởi chạy PostgreSQL và Redis trên máy của bạn.

### Bước 4: Chạy ứng dụng Spring Boot
Bạn có hai cách để chạy:
- **Cách 1 (Sử dụng Spring Boot Dashboard):** Click vào biểu tượng Spring Boot ở thanh sidebar bên trái, tìm ứng dụng `fms-app` và nhấn nút **Run**.
- **Cách 2 (Sử dụng Terminal):**
  ```bash
  ./gradlew bootRun
  ```

---

## 4. Cấu hình và Chạy Frontend

### Bước 1: Mở thư mục frontend
Mở một cửa sổ terminal mới và di chuyển vào thư mục `fms_frontend`:
```bash
cd fms_frontend
```

### Bước 2: Cài đặt các thư viện phụ thuộc
Chạy lệnh sau để cài đặt các package cần thiết:
```bash
npm install
```

### Bước 3: Chạy ứng dụng React
Sau khi cài đặt xong, khởi chạy dự án bằng lệnh:
```bash
npm run dev
```
Mặc định ứng dụng sẽ chạy tại địa chỉ: [http://localhost:5173](http://localhost:5173)

---

## 5. Lưu ý
- Đảm bảo Docker đang chạy trước khi khởi động Backend.
- Nếu gặp lỗi về quyền thực thi đối với `gradlew`, hãy chạy lệnh: `chmod +x gradlew` (trên Linux/macOS).
- Kiểm tra cổng (Port) trong file `.env` và `application.yaml` để tránh xung đột với các ứng dụng khác.
