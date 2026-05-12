# ARCH_MAP - BẢN ĐỒ KIẾN TRÚC HỆ THỐNG

> **Mục đích:** Tài liệu sống đóng vai trò là nguồn sự thật duy nhất cho bối cảnh kỹ thuật của dự án. AI phải đọc trước mỗi tác vụ lập trình và cập nhật ngay sau đó.

---

## 1. Tổng quan Công nghệ (Tech Stack)

- **Ngôn ngữ/Framework Backend:** Java 17 + Spring Boot 3.5.14 (Gradle Kotlin DSL, đa module)
- **Ngôn ngữ/Framework Frontend:** TypeScript 6.0 + React 19.2 + Vite 8.0
- **Cơ sở dữ liệu:** PostgreSQL 16 (qua Docker), Redis 7 (cache/session)
- **Thư viện Backend chính:** Spring Data JPA (Hibernate), Spring Security, MapStruct 1.5.5, Lombok 1.18.32, JJWT 0.12.6 (JWT), spring-dotenv 4.0.0
- **Thư viện Frontend chính:** Ant Design 6.3.7, React Router DOM 7.15, Zustand 5.0 (quản lý trạng thái), TanStack React Query 5.100 (fetch/cache dữ liệu server), Axios 1.16 (HTTP client), Recharts 3.8 (biểu đồ), Tailwind Merge 3.6

---

## 2. Bản đồ Thư mục & Trách nhiệm

- **`fms_backend/`**: Dự án Spring Boot đa module – toàn bộ logic nghiệp vụ và API.
  - **`fms-app/`**: Module chính, điểm vào ứng dụng (Spring Boot application). Đóng gói thành fat JAR, kéo tất cả module nghiệp vụ làm phụ thuộc. Chứa cấu hình `application.yaml`.
  - **`fms-common/`**: Thư viện dùng chung. Cung cấp các dependency cốt lõi: Spring Data JPA, Spring Security, Validation, Web, JWT. Chứa các entity base, utility, cấu hình bảo mật dùng chung.
  - **`fms-user/`**: Module quản lý người dùng, xác thực, phân quyền (User, Role, Permission).
  - **`fms-customer/`**: Module quản lý khách hàng (Customer, Contact).
  - **`fms-order/`**: Module quản lý đơn hàng, báo giá (Order, Quotation).
  - **`fms-production/`**: Module quản lý sản xuất, lệnh sản xuất, công đoạn (Production Order, Workstation).
  - **`fms-inventory/`**: Module quản lý kho, nguyên vật liệu, thành phẩm (Material, Product, Stock).
- **`fms_frontend/`**: Ứng dụng React (SPA) – giao diện người dùng.
  - **`src/`**: Mã nguồn chính (hiện tại là template khởi tạo, sẽ mở rộng với các thư mục `components/`, `pages/`, `hooks/`, `stores/`, `api/`).
  - **`public/`**: Tài nguyên tĩnh.
- **`.agent/`**: Tài liệu kiến trúc, bản đồ hệ thống.
- **`.clinerules/`**: Quy tắc an toàn và hướng dẫn cho AI.

---

## 3. Các Thực thể Dữ liệu Cốt lõi

> Dựa trên cấu trúc module và nghiệp vụ sản xuất nội thất. Các thực thể này sẽ được ánh xạ qua JPA Entity trong từng module.

- **User** (`fms-user`): `{ id: Long, username: string, passwordHash: string, email: string, fullName: string, role: Role, enabled: boolean }`
- **Role** (`fms-user`): `{ id: Long, name: string (ADMIN, MANAGER, OPERATOR, VIEWER), permissions: List<Permission> }`
- **Customer** (`fms-customer`): `{ id: Long, name: string, phone: string, email: string, address: string, taxCode: string }`
- **Order/Quotation** (`fms-order`): `{ id: Long, customerId: Long, orderType: enum, totalAmount: BigDecimal, status: enum, createdDate: LocalDateTime, items: List<OrderItem> }`
- **OrderItem** (`fms-order`): `{ id: Long, orderId: Long, productId: Long, quantity: int, unitPrice: BigDecimal, note: string }`
- **Product** (`fms-inventory`): `{ id: Long, sku: string, name: string, category: string, unit: string, costPrice: BigDecimal, sellingPrice: BigDecimal }`
- **Material** (`fms-inventory`): `{ id: Long, sku: string, name: string, unit: string, currentStock: double, minStock: double }`
- **ProductionOrder** (`fms-production`): `{ id: Long, orderId: Long, productId: Long, quantity: int, startDate: LocalDate, dueDate: LocalDate, status: enum, workstationId: Long }`
- **Workstation** (`fms-production`): `{ id: Long, name: string, type: string, capacity: int }`

---

## 4. Luồng Dữ liệu Quan trọng

- **Luồng Xác thực & Phân quyền:**
  1. Frontend gửi thông tin đăng nhập (username/password) đến `/api/auth/login`.
  2. `fms-user` (qua Spring Security) xác thực, trả về JWT (access token + refresh token).
  3. Frontend lưu token, gắn vào header `Authorization: Bearer <token>` cho mọi request sau.
  4. `fms-common` (OncePerRequestFilter) kiểm tra token, trích xuất quyền từ token, thiết lập SecurityContext.

- **Luồng Tạo Đơn hàng → Sản xuất:**
  1. Frontend gọi API `POST /api/orders` với dữ liệu đơn hàng (khách hàng, sản phẩm, số lượng).
  2. `fms-order` tạo Order, cập nhật trạng thái "PENDING".
  3. Khi đơn hàng được duyệt (status → "CONFIRMED"), `fms-order` gọi sang `fms-production` để tạo `ProductionOrder`.
  4. `fms-production` lập lịch sản xuất, kiểm tra `fms-inventory` về nguyên vật liệu.
  5. `fms-inventory` cập nhật tồn kho khi xuất nguyên vật liệu và nhập thành phẩm.

- **Luồng Đồng bộ dữ liệu Frontend ↔ Backend:**
  1. React Query (`@tanstack/react-query`) quản lý việc fetch, cache, và tự động cập nhật dữ liệu từ REST API.
  2. Zustand (`zustand`) quản lý trạng thái toàn cục phía client (thông tin user đang đăng nhập, theme, cấu hình UI) – không dùng cho dữ liệu server.

- **Luồng Giao tiếp giữa các Module:**
  - Các module giao tiếp trực tiếp qua method call (cùng JVM) vì được đóng gói chung trong `fms-app`. Không sử dụng REST/MQ nội bộ.
  - `fms-common` cung cấp các interface/service chung mà mọi module có thể sử dụng (VD: `BaseEntity`, `BaseRepository`, `JwtService`).

- **Luồng Dữ liệu CSDL:**
  - Tất cả module dùng chung một database PostgreSQL (`fms_db`). Mỗi module sở hữu các bảng riêng (prefix theo module hoặc schema).
  - Hibernate `ddl-auto: update` được sử dụng trong môi trường phát triển để tự động tạo/cập nhật schema.

---

## 5. Quy ước & Ràng buộc

- **Ngôn ngữ:**
  - Tất cả chú thích, docstring, log, tài liệu (bao gồm ARCH_MAP.md và các comment trong code) phải bằng **Tiếng Việt**.
  - Mã nguồn (tên biến, hàm, lớp, package) sử dụng **Tiếng Anh** theo chuẩn Java/TypeScript.

- **Tổ chức Backend (Java/Spring Boot):**
  - Package naming: `com.uth.fms.<module>` (VD: `com.uth.fms.user.entity`, `com.uth.fms.order.service`).
  - Mỗi module nghiệp vụ phụ thuộc vào `fms-common`, không phụ thuộc lẫn nhau trừ khi thực sự cần thiết.
  - Sử dụng Lombok để giảm boilerplate (`@Data`, `@Builder`, `@RequiredArgsConstructor`).
  - MapStruct được dùng để chuyển đổi giữa Entity và DTO.
  - Xử lý lỗi: Luôn bọc lỗi trong `RuntimeException` tùy chỉnh (VD: `ResourceNotFoundException`, `BusinessException`) với thông báo rõ ràng. Sử dụng `@ControllerAdvice` để xử lý lỗi toàn cục.
  - Bảo mật: Tuân thủ quy tắc an toàn trong `.clinerules/`:
    - **Chống SQL Injection**: Spring Data JPA (tham số hóa tự động).
    - **XSS**: Mã hóa đầu ra; API trả về JSON, không render HTML trực tiếp từ dữ liệu người dùng.
    - **Bí mật**: Không hardcode; dùng `.env` và biến môi trường (`spring-dotenv`). File `.env` không được commit.
    - **JWT**: Access token hết hạn 15 phút, refresh token 8 giờ; lưu trữ an toàn phía client.

- **Tổ chức Frontend (React/TypeScript):**
  - Sử dụng functional components và hooks.
  - Quản lý trạng thái server: **TanStack React Query** – mọi dữ liệu fetch từ API phải qua React Query.
  - Quản lý trạng thái client toàn cục: **Zustand** – không lạm dụng `useState` cho dữ liệu chia sẻ giữa các component không liên quan.
  - UI Library: **Ant Design** (`antd`) – ưu tiên dùng component có sẵn, hạn chế CSS tùy chỉnh.
  - Routing: **React Router DOM v7**.
  - HTTP Client: **Axios** với interceptor để tự động gắn JWT token.
  - Styles: CSS Modules hoặc Tailwind CSS (qua `tailwind-merge`).

- **Cấu trúc thư mục Frontend (dự kiến):**
  - `src/api/`: Cấu hình Axios, các hàm gọi API.
  - `src/components/`: Component tái sử dụng (UI components).
  - `src/pages/`: Trang chính, mỗi trang tương ứng với một route.
  - `src/hooks/`: Custom hooks.
  - `src/stores/`: Zustand stores.
  - `src/types/`: Định nghĩa TypeScript interface/type (DTO).

- **Cơ sở dữ liệu & Migration:**
  - Dùng PostgreSQL 16. Cổng mặc định 5432.
  - Hibernate `ddl-auto: update` cho dev; sẽ chuyển sang công cụ migration (Flyway/Liquibase) khi lên production.
  - Tên bảng: snake_case, plural (VD: `users`, `production_orders`).

- **Ràng buộc khác:**
  - Không tự ý chạy lệnh Git, push, SSH, hoặc gửi dữ liệu ra ngoài khi chưa được phê duyệt.
  - Mọi thay đổi kiến trúc phải được cập nhật vào ARCH_MAP.md.

---

## 6. Nhật ký Thay đổi & Lộ trình

- `[2026-05-12]` - `[Khởi tạo]`: AI thực hiện quét ban đầu và tạo bản đồ hệ thống ARCH_MAP.md dựa trên cấu trúc dự án hiện tại.
- `[TODO]`: Triển khai các entity và repository cơ bản trong từng module.
- `[TODO]`: Thiết lập Spring Security với JWT đầy đủ trong `fms-common` và `fms-user`.
- `[TODO]`: Xây dựng cấu trúc thư mục frontend (pages, components, api, stores) và kết nối API đầu tiên.
- `[TODO]`: Thiết lập CI/CD pipeline (GitHub Actions hoặc tương tự).

---
_Cập nhật lần cuối: `2026-05-12`_
_Được duy trì bởi: Giao thức Kiến trúc sư AI v1.0_
