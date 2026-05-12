package com.uth.fms.order.entity;

import com.uth.fms.common.enums.OrderStatus;
import com.uth.fms.common.enums.PriceStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity đơn hàng sau khi khách chốt báo giá.
 * Lưu trữ thông tin đơn hàng, trạng thái sản xuất/giao hàng, thanh toán.
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    /** Khóa chính tự tăng */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /** Mã đơn hàng theo định dạng DH-YYYY-XXXX */
    @Column(name = "code", unique = true, nullable = false, length = 30)
    String code;

    /** Báo giá gốc đã được chuyển đổi (FK → quotations.id), NULL nếu tạo trực tiếp */
    @Column(name = "quotation_id")
    Long quotation_id;

    /** Khách hàng đặt đơn (FK → customers.id) */
    @Column(name = "customer_id", nullable = false)
    Long customer_id;

    /** Sale phụ trách đơn hàng (FK → users.id) */
    @Column(name = "sale_id", nullable = false)
    Long sale_id;

    /**
     * Trạng thái vòng đời đơn hàng:
     * CONFIRMED → WAITING_DESIGN → IN_PRODUCTION → WAITING_DELIVERY →
     * DELIVERING → COMPLETED / CANCELLED
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    OrderStatus status;

    /** Loại giá: FIXED (chốt cứng) hoặc ESTIMATED (chờ chốt BOM cuối cùng) */
    @Enumerated(EnumType.STRING)
    @Column(name = "price_type", nullable = false, length = 10)
    PriceStatus price_type;

    /** Tổng tiền theo hợp đồng đã ký */
    @Column(name = "total_amount", precision = 15, scale = 2)
    BigDecimal total_amount;

    /** Giá cuối cùng sau khi chốt BOM thực tế (chỉ có ý nghĩa khi ESTIMATED) */
    @Column(name = "final_amount", precision = 15, scale = 2)
    BigDecimal final_amount;

    /** Tổng tiền khách đã đặt cọc, mặc định 0 */
    @Column(name = "deposit_amount", precision = 15, scale = 2)
    @Builder.Default
    BigDecimal deposit_amount = BigDecimal.ZERO;

    /** Lệnh sản xuất hiện tại đang xử lý đơn hàng (FK → production_orders.id) */
    @Column(name = "current_production_id")
    Long current_production_id;

    /** Ngày dự kiến giao hàng cho khách */
    @Column(name = "expected_delivery_date")
    LocalDate expected_delivery_date;

    /** Ngày giao hàng thực tế (điền khi giao xong) */
    @Column(name = "actual_delivery_date")
    LocalDate actual_delivery_date;

    /** Lý do hủy đơn hàng (chỉ điền khi status = CANCELLED) */
    @Column(name = "cancel_reason", columnDefinition = "TEXT")
    String cancel_reason;

    /**
     * Phân loại nguyên nhân hủy: FACTORY_ERROR / CUSTOMER_REQUEST.
     * Ghi chú từ thiết kế DB: đây KHÔNG phải enum.
     */
    @Column(name = "cancel_type", length = 20)
    String cancel_type;

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
