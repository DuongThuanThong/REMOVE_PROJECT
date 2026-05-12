package com.uth.fms.common.entity;

import com.uth.fms.common.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * Entity thông báo hệ thống.
 * Lưu trữ các thông báo gửi đến người dùng về task, đơn hàng, tồn kho...
 */
@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {

    /** Khóa chính tự tăng */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /** Người nhận thông báo (FK → users.id) */
    @Column(name = "recipient_id", nullable = false)
    Long recipient_id;

    /** Loại thông báo: phân công task, tắc nghẽn, QC lỗi, duyệt đơn, tồn kho thấp, cảnh báo deadline */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 50)
    NotificationType type;

    /** Tiêu đề thông báo ngắn gọn */
    @Column(name = "title", nullable = false, length = 200)
    String title;

    /** Nội dung chi tiết của thông báo */
    @Column(name = "message", columnDefinition = "TEXT")
    String message;

    /** Loại đối tượng liên quan (VD: ORDER, TASK, PRODUCTION_ORDER) */
    @Column(name = "ref_type", length = 30)
    String ref_type;

    /** ID của đối tượng liên quan */
    @Column(name = "ref_id")
    Long ref_id;

    /** Trạng thái đã đọc (false = chưa đọc) */
    @Column(name = "is_read", nullable = false)
    @Builder.Default
    Boolean is_read = false;

    /** Thời điểm gửi thông báo */
    @Column(name = "created_at", nullable = false)
    @Builder.Default
    LocalDateTime created_at = LocalDateTime.now();
}
