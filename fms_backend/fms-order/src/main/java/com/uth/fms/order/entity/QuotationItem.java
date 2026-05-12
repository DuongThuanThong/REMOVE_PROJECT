package com.uth.fms.order.entity;

import com.uth.fms.common.enums.BomType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * Entity dòng sản phẩm trong báo giá.
 * Mỗi dòng đại diện một sản phẩm được báo giá kèm số lượng, đơn giá.
 */
@Entity
@Table(name = "quotation_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuotationItem {

    /** Khóa chính tự tăng */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /** Báo giá chứa dòng này (FK → quotations.id) */
    @Column(name = "quotation_id", nullable = false)
    Long quotation_id;

    /** Sản phẩm tiêu chuẩn tham chiếu (FK → product_templates.id), NULL nếu MANUAL */
    @Column(name = "product_template_id")
    Long product_template_id;

    /** Tên sản phẩm tại thời điểm báo giá (lưu snapshot tránh đổi tên sau này) */
    @Column(name = "product_name", nullable = false, length = 200)
    String product_name;

    /** Cách tính BOM: AUTO (theo template) hoặc MANUAL (thiết kế riêng) */
    @Enumerated(EnumType.STRING)
    @Column(name = "bom_type", nullable = false, length = 10)
    BomType bom_type;

    /** Số lượng sản phẩm */
    @Column(name = "quantity", nullable = false)
    Integer quantity;

    /** Đơn giá một sản phẩm tại thời điểm báo giá */
    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    BigDecimal unit_price;

    /** Thành tiền = quantity × unit_price */
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    BigDecimal amount;

    /** Ghi chú yêu cầu tùy chỉnh riêng (chỉ có ý nghĩa khi bom_type = MANUAL) */
    @Column(name = "custom_note", columnDefinition = "TEXT")
    String custom_note;
}
