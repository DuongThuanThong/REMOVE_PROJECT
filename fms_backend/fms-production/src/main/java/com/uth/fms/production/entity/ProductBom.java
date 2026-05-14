package com.uth.fms.production.entity;

import com.uth.fms.common.entity.BaseEntity;
// import com.uth.fms.material.entity.Material;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product_boms")
@EntityListeners(AuditingEntityListener.class)
public class ProductBom extends BaseEntity {

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "product_template_id", nullable = false,
    // foreignKey = @ForeignKey(name = "fk_product_boms_product_template"))
    // private ProductTemplate productTemplate;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "material_id", nullable = false,
    // foreignKey = @ForeignKey(name = "fk_product_boms_material"))
    // private Material material;

    @Column(nullable = false, precision = 10, scale = 4, name = "qty_per_unit")
    private BigDecimal qtyPerUnit;

    @Builder.Default
    @Column(precision = 5, scale = 4, name = "waste_factor")
    private BigDecimal wasteFactor = BigDecimal.valueOf(0.05);
}