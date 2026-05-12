package com.uth.fms.order.entity;

import com.uth.fms.common.enums.PriceStatus;
import com.uth.fms.common.enums.QuotationStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity báo giá gửi cho khách hàng.
 * Lưu trữ thông tin báo giá: mã, khách hàng, sale phụ trách, trạng thái duyệt...
 */
@Entity
@Table(name = "quotations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quotation {

    /** Khóa chính tự tăng */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /** Mã báo giá theo định dạng BG-YYYY-XXXX */
    @Column(name = "code", unique = true, nullable = false, length = 30)
    String code;

    /** Khách hàng nhận báo giá (FK → customers.id) */
    @Column(name = "customer_id", nullable = false)
    Long customer_id;

    /** Sale phụ trách báo giá (FK → users.id) */
    @Column(name = "sale_id", nullable = false)
    Long sale_id;

    /** Loại giá: FIXED (chốt cứng) hoặc ESTIMATED (tạm tính, chờ chốt BOM) */
    @Enumerated(EnumType.STRING)
    @Column(name = "price_type", nullable = false, length = 10)
    PriceStatus price_type;

    /** Tổng tiền trước chiết khấu */
    @Column(name = "subtotal", precision = 15, scale = 2)
    BigDecimal subtotal;

    /** Số tiền chiết khấu, mặc định 0 */
    @Column(name = "discount", precision = 15, scale = 2)
    @Builder.Default
    BigDecimal discount = BigDecimal.ZERO;

    /** Tổng tiền sau chiết khấu = subtotal - discount */
    @Column(name = "total_amount", precision = 15, scale = 2)
    BigDecimal total_amount;

    /** Giá chốt cuối cùng sau khi thương lượng (chỉ dùng khi ESTIMATED) */
    @Column(name = "final_quoted_amount", precision = 15, scale = 2)
    BigDecimal final_quoted_amount;

    /** Cờ yêu cầu Giám đốc duyệt khi giá vượt ngưỡng cho phép */
    @Column(name = "requires_director_approval")
    @Builder.Default
    Boolean requires_director_approval = false;

    /**
     * Trạng thái vòng đời báo giá:
     * DRAFT → PENDING_APPROVAL → APPROVED/REJECTED → SENT → ACCEPTED → CONVERTED
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    QuotationStatus status;

    /** Ngày hết hạn hiệu lực của báo giá */
    @Column(name = "valid_until")
    LocalDate valid_until;

    /** Giám đốc đã duyệt/từ chối (FK → users.id) */
    @Column(name = "approved_by")
    Long approved_by;

    /** Soft delete */
    @Column(name = "is_deleted")
    @Builder.Default
    Boolean is_deleted = false;

    /** Ngày tạo */
    @Column(name = "created_at", nullable = false)
    @Builder.Default
    LocalDateTime created_at = LocalDateTime.now();

    /** Ngày cập nhật */
    @Column(name = "updated_at")
    LocalDateTime updated_at;
}
