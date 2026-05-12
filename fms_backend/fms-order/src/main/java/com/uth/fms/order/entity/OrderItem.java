package com.uth.fms.order.entity;

import com.uth.fms.common.enums.BomType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * Entity dòng sản phẩm trong đơn hàng.
 * Mỗi dòng đại diện một sản phẩm đã chốt đơn kèm số lượng, đơn giá bất biến.
 */
@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {

    /** Khóa chính tự tăng */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /** Đơn hàng chứa dòng sản phẩm này (FK → orders.id) */
    @Column(name = "order_id", nullable = false)
    Long order_id;

    /** Sản phẩm tiêu chuẩn tham chiếu (FK → product_templates.id), NULL nếu MANUAL */
    @Column(name = "product_template_id")
    Long product_template_id;

    /** Tên sản phẩm tại thời điểm chốt đơn (lưu snapshot bất biến) */
    @Column(name = "product_name", nullable = false, length = 200)
    String product_name;

    /** Cách tính BOM: AUTO (theo template) hoặc MANUAL (thiết kế riêng) */
    @Enumerated(EnumType.STRING)
    @Column(name = "bom_type", nullable = false, length = 10)
    BomType bom_type;

    /** Số lượng sản phẩm đã chốt */
    @Column(name = "quantity", nullable = false)
    Integer quantity;

    /** Đơn giá một sản phẩm tại thời điểm chốt đơn (bất biến sau khi chốt) */
    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    BigDecimal unit_price;

    /** Thành tiền = quantity × unit_price */
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    BigDecimal amount;

    /** Ghi chú yêu cầu tùy chỉnh riêng (bất biến sau khi chốt đơn) */
    @Column(name = "custom_note", columnDefinition = "TEXT")
    String custom_note;
}
