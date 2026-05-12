package com.uth.fms.order.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity ghi nhận các lần thay đổi đơn hàng (revision).
 * Mỗi lần khách yêu cầu thay đổi thiết kế/số lượng/giá sẽ tạo một revision mới.
 */
@Entity
@Table(name = "order_revisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRevision {

    /** Khóa chính tự tăng */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /** Đơn hàng được thay đổi (FK → orders.id) */
    @Column(name = "order_id", nullable = false)
    Long order_id;

    /** Số thứ tự lần thay đổi, bắt đầu từ 1 và tăng dần theo từng đơn hàng */
    @Column(name = "revision_number", nullable = false)
    Integer revision_number;

    /** Mô tả chi tiết nội dung thay đổi (VD: đổi kích thước tủ, thêm ngăn kéo...) */
    @Column(name = "change_description", columnDefinition = "TEXT")
    String change_description;

    /** Giá mới sau khi thay đổi (nếu có thay đổi về giá) */
    @Column(name = "new_amount", precision = 15, scale = 2)
    BigDecimal new_amount;

    /** Khách hàng đã xác nhận đồng ý với thay đổi này chưa */
    @Column(name = "customer_confirmed")
    @Builder.Default
    Boolean customer_confirmed = false;

    /** Thời điểm khách hàng xác nhận thay đổi */
    @Column(name = "customer_confirmed_at")
    LocalDateTime customer_confirmed_at;

    /** Sale tạo revision (FK → users.id) */
    @Column(name = "created_by", nullable = false)
    Long created_by;

    /** Thời điểm tạo revision */
    @Column(name = "created_at", nullable = false)
    @Builder.Default
    LocalDateTime created_at = LocalDateTime.now();
}
