# QUY TẮC LẬP TRÌNH AN TOÀN CHO TÁC NHÂN AI

## Bắt buộc — Không có Ngoại lệ

---

## 1. MỤC ĐÍCH

Các quy tắc này là **bắt buộc** cho mọi hoạt động tạo mã, đánh giá, và khuyến nghị kỹ thuật của Tác nhân AI. Vi phạm được xem là **Lỗi Nghiêm trọng** đòi hỏi khắc phục ngay lập tức.

---

## 2. QUY TẮC CỐT LÕI

### R1: Chống SQL Injection

- **CẤM:** Không bao giờ sử dụng nối chuỗi hoặc nội suy để xây dựng SQL từ đầu vào của người dùng.
- **YÊU CẦU:** Sử dụng truy vấn có tham số hoặc ORM/Trình xây dựng Truy vấn đã xác minh (Entity Framework, Dapper, GORM, Eloquent, SQLAlchemy, Prisma).
- _Sai:_ `$"SELECT * FROM Users WHERE id = {id}"`
- _Đúng:_ `SELECT * FROM Users WHERE id = @id` với tham số đã ràng buộc

### R2: Kiểm soát Đầu vào/Đầu ra (XSS/Injection)

- **ĐẦU VÀO:** Coi mọi dữ liệu bên ngoài là độc hại. Xác thực kiểu, độ dài, định dạng, và danh sách trắng trước khi xử lý.
- **ĐẦU RA:** Mã hóa tất cả dữ liệu trước khi hiển thị (Mã hóa HTML). Không bao giờ hiển thị HTML thô trừ khi đã được làm sạch bằng thư viện đáng tin cậy (DOMPurify, bleach).
- **ĐẶC THÙ AI:** Bảo vệ chống **Prompt Injection**. Không thực thi mã hoặc truy vấn chỉ dựa trên yêu cầu của người dùng mà không xác minh.

### R3: Quản lý Bí mật

- **CẤM:** Không bao giờ mã hóa cứng khóa API, chuỗi kết nối, mật khẩu, hoặc bí mật trong mã nguồn.
- **YÊU CẦU:** Sử dụng biến môi trường hoặc dịch vụ quản lý bí mật (Azure Key Vault, AWS Secrets Manager, HashiCorp Vault).
- **GHI NHẬT KÝ:** Không bao giờ ghi nhật ký dữ liệu nhạy cảm của người dùng (mật khẩu, thẻ tín dụng, token, PII).
- **ĐẶC THÙ AI:** Không lưu cache dữ liệu nhạy cảm của người dùng trong cửa sổ ngữ cảnh. Không ghi nhật ký nội dung cuộc trò chuyện chứa dữ liệu nhạy cảm.

### R4: Kiểm soát Truy cập

- Xác minh ủy quyền **phía máy chủ** cho mọi yêu cầu. Không bao giờ tin tưởng khả năng hiển thị UI phía khách hàng.
- Sử dụng RBAC hoặc ABAC. Tất cả các endpoint phải yêu cầu xác thực trừ khi cố ý công khai.

### R5: Xử lý Lỗi An toàn

- **CẤM:** Không bao giờ phơi bày stack trace, chi tiết lỗi hệ thống, lược đồ cơ sở dữ liệu, hoặc đường dẫn file cho người dùng cuối trong môi trường sản xuất.
- **YÊU CẦU:** Trả về lỗi thân thiện với người dùng chung chung. Ghi nhật ký lỗi chi tiết phía máy chủ để kỹ sư điều tra.
- Ẩn phiên bản framework/máy chủ khỏi HTTP headers.

### R6: Bảo mật Phiên làm việc

- Sử dụng JWT/token phiên với hạn sử dụng, chữ ký, và mã hóa khi cần.
- Triển khai cookie **HttpOnly**, **Secure**, **SameSite=Strict**.
- Cung cấp đăng xuất và thu hồi token. Tái tạo ID phiên sau khi đăng nhập.

### R7: Bảo vệ Tấn công

- **Giới hạn Tốc độ:** Giới hạn số yêu cầu trong khung thời gian để ngăn brute-force và DDoS tầng ứng dụng.
- **CSRF:** Sử dụng token CSRF cho các yêu cầu thay đổi trạng thái.
- **Clickjacking:** Đặt `X-Frame-Options: DENY` hoặc `CSP: frame-ancestors 'none'`.
- **HTTP Headers Bảo mật:** Thực thi `Content-Security-Policy`, `X-Content-Type-Options: nosniff`, `Referrer-Policy`.

### R8: An toàn Phụ thuộc

- Chỉ sử dụng nguồn đáng tin cậy (npm, PyPI, Maven, NuGet) với phiên bản ổn định.
- Quét lỗ hổng định kỳ (npm audit, Dependabot, Snyk, OWASP Dependency-Check).
- Không bao giờ sử dụng thư viện bị bỏ rơi hoặc lỗ hổng nghiêm trọng chưa vá.

### R9: Mã hóa Dữ liệu

- **Truyền tải:** Thực thi TLS 1.2+ cho mọi kết nối mạng. Cấm HTTP không mã hóa trong sản xuất.
- **Lưu trữ:** Mã hóa/băm dữ liệu nhạy cảm bằng thuật toán mạnh (bcrypt, Argon2, AES-256).
- **CẤM:** Thuật toán yếu — MD5, SHA1, DES, RC4.

### R10: Ghi nhật ký Kiểm toán

- Ghi nhật ký các hành động quan trọng: đăng nhập/đăng xuất, thay đổi quyền, xóa dữ liệu, truy cập dữ liệu nhạy cảm.
- Đảm bảo tính toàn vẹn nhật ký (chống giả mạo), bao gồm dấu thời gian và danh tính tác nhân.
- Cảnh báo khi phát hiện hành vi bất thường.

---

## 3. DANH SÁCH KIỂM TRA TRƯỚC KHI GỬI

- [ ] SQL sử dụng truy vấn có tham số hoặc ORM?
- [ ] Đầu vào đã được xác thực (kiểu, độ dài, định dạng, danh sách trắng)?
- [ ] Đầu ra đã được mã hóa/làm sạch trước khi hiển thị?
- [ ] Không có bí mật mã hóa cứng (khóa API, mật khẩu, chuỗi kết nối)?
- [ ] Nhật ký không chứa dữ liệu nhạy cảm?
- [ ] Ủy quyền phía máy chủ đã được kiểm tra cho mọi endpoint?
- [ ] Lỗi là chung chung với người dùng, chi tiết chỉ trong nhật ký máy chủ?
- [ ] Phiên/token có hạn sử dụng và thu hồi?
- [ ] Giới hạn tốc độ đã được áp dụng cho các endpoint quan trọng?
- [ ] Phụ thuộc đã được quét và không có lỗ hổng?
- [ ] TLS 1.2+ được thực thi cho mọi kết nối?
- [ ] Dữ liệu nhạy cảm đã được mã hóa/băm khi lưu trữ?
- [ ] Các hành động quan trọng đã được ghi trong nhật ký kiểm toán?
- [ ] **AI:** Prompt injection đã được kiểm tra và giảm thiểu?
- [ ] **AI:** Không có dữ liệu nhạy cảm được lưu cache trong ngữ cảnh?
- [ ] **AI:** Không có mã hóa cứng không an toàn hoặc cấu hình không bảo mật được đề xuất?
- [ ] **AI:** Mã nguồn tuân theo nguyên tắc đặc quyền tối thiểu?

---

## 4. QUY TẮC VÀNG

1. **Không Tin tưởng** — Mọi đầu vào là độc hại cho đến khi được chứng minh ngược lại.
2. **Phòng thủ theo Chiều sâu** — Không bao giờ dựa vào một lớp bảo vệ duy nhất.
3. **Thất bại An toàn** — Khi thất bại, mặc định từ chối.
4. **Đặc quyền Tối thiểu** — Cấp quyền tối thiểu cần thiết.
5. **Không bao giờ Tin tưởng Khách hàng** — Mọi kiểm tra bảo mật thuộc về phía máy chủ.
6. **An toàn theo Mặc định** — Cấu hình mặc định phải là an toàn nhất, không phải thuận tiện nhất.

---

## 5. XỬ LÝ VI PHẠM

- Mọi vi phạm đều là lỗi **Mức độ Nghiêm trọng Cao**.
- **Yêu cầu sửa chữa ngay lập tức** trước khi merge/triển khai.
- Phát hiện trong sản xuất kích hoạt **Phản ứng Sự cố** với rollback nếu cần.

---

_Tài liệu sống — được cập nhật khi có mối đe dọa hoặc công nghệ mới. Tác nhân phải luôn áp dụng phiên bản mới nhất._
