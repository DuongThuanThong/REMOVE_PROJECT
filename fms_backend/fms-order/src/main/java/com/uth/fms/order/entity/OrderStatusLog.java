package com.uth.fms.order.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * Entity nhật ký thay đổi trạng thái đơn hàng.
 * Ghi lại mỗi lần trạng thái đơn hàng chuyển từ A → B, ai thực hiện và lý do.
 */
@Entity
@Table(name = "order_status_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderStatusLog {

    /** Khóa chính tự tăng */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /** Đơn hàng được thay đổi trạng thái (FK → orders.id) */
    @Column(name = "order_id", nullable = false)
    Long order_id;

    /** Trạng thái trước khi chuyển (NULL nếu là lần tạo đầu tiên) */
    @Column(name = "from_status", length = 30)
    String from_status;

    /** Trạng thái mới sau khi chuyển */
    @Column(name = "to_status", nullable = false, length = 30)
    String to_status;

    /** Lý do chuyển trạng thái hoặc ghi chú kèm theo */
    @Column(name = "note", columnDefinition = "TEXT")
    String note;

    /** Người thực hiện chuyển trạng thái (FK → users.id) */
    @Column(name = "changed_by", nullable = false)
    Long changed_by;

    /** Thời điểm chuyển trạng thái */
    @Column(name = "changed_at", nullable = false)
    @Builder.Default
    LocalDateTime changed_at = LocalDateTime.now();
}
