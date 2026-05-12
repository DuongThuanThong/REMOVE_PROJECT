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
@Table(name = "material_waste")
public class MaterialWaste extends BaseEntity {

    @Column(name = "production_order_id", nullable = false)
    Long productionOrderId;

    @Column(name = "task_id")
    Long taskId;

    @Column(name = "material_id", nullable = false)
    Long materialId;

    @Column(name = "qty_wasted", nullable = false)
    BigDecimal qtyWasted;

    @Column(name = "waste_type", nullable = false, length = 20)
    String wasteType;
}
