package com.uth.fms.production.entity;

import com.uth.fms.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "material_requirements")
public class MaterialRequirement extends BaseEntity {

    @Column(name = "production_order_id", nullable = false)
    Long productionOrderId;

    @Column(name = "material_id", nullable = false)
    Long materialId;

    @Column(name = "qty_required", nullable = false)
    BigDecimal qtyRequired;

    @Builder.Default
    @Column(name = "qty_issued")
    BigDecimal qtyIssued = BigDecimal.ZERO;

    @Column(nullable = false, length = 10)
    String source;

    @Builder.Default
    @Column(name = "revision_number")
    Integer revisionNumber = 1;
}
