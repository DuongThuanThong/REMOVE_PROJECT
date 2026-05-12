package com.uth.fms.order.entity;

import com.uth.fms.common.enums.PaymentMethod;
import com.uth.fms.common.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity giao dịch thanh toán của đơn hàng.
 * Ghi nhận các khoản thu (cọc, thanh toán, phí thiết kế) và hoàn tiền.
 */
@Entity
@Table(name = "payment_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentTransaction {

    /** Khóa chính tự tăng */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /** Đơn hàng liên quan đến giao dịch (FK → orders.id) */
    @Column(name = "order_id", nullable = false)
    Long order_id;

    /**
     * Loại giao dịch:
     * DEPOSIT (đặt cọc), DESIGN_FEE (phí thiết kế),
     * FINAL_PAYMENT (thanh toán cuối), REFUND (hoàn tiền)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    PaymentType type;

    /** Phương thức thanh toán: CASH (tiền mặt) hoặc BANK_TRANSFER (chuyển khoản) */
    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false, length = 20)
    PaymentMethod method;

    /**
     * Số tiền giao dịch.
     * Dương (+) cho các khoản thu (DEPOSIT, DESIGN_FEE, FINAL_PAYMENT).
     * Âm (-) cho các khoản hoàn (REFUND).
     */
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    BigDecimal amount;

    /** Đường dẫn ảnh biên lai/chứng từ thanh toán trên MinIO */
    @Column(name = "receipt_image_url", length = 500)
    String receipt_image_url;

    /** Người ghi nhận giao dịch (FK → users.id) */
    @Column(name = "recorded_by", nullable = false)
    Long recorded_by;

    /** Thời điểm ghi nhận giao dịch */
    @Column(name = "created_at", nullable = false)
    @Builder.Default
    LocalDateTime created_at = LocalDateTime.now();
}
