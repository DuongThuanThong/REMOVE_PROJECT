package com.uth.fms.order.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity nhật ký thay đổi deadline giao hàng.
 * Ghi lại mỗi lần dời ngày giao dự kiến, kèm lý do và người thực hiện.
 */
@Entity
@Table(name = "deadline_change_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeadlineChangeLog {

    /** Khóa chính tự tăng */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /** Đơn hàng được thay đổi deadline (FK → orders.id) */
    @Column(name = "order_id", nullable = false)
    Long order_id;

    /** Ngày giao dự kiến cũ trước khi thay đổi */
    @Column(name = "old_deadline", nullable = false)
    LocalDate old_deadline;

    /** Ngày giao dự kiến mới sau khi thay đổi */
    @Column(name = "new_deadline", nullable = false)
    LocalDate new_deadline;

    /** Lý do dời deadline (VD: thiếu NVL, quá tải SX, khách yêu cầu...) */
    @Column(name = "reason_note", nullable = false, columnDefinition = "TEXT")
    String reason_note;

    /** Người thực hiện thay đổi deadline (FK → users.id) */
    @Column(name = "changed_by", nullable = false)
    Long changed_by;

    /** Thời điểm thay đổi deadline */
    @Column(name = "changed_at", nullable = false)
    @Builder.Default
    LocalDateTime changed_at = LocalDateTime.now();
}
