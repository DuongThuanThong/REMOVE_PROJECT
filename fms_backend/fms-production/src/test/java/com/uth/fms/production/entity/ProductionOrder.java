package com.uth.fms.production.entity;

import com.uth.fms.common.entity.BaseEntity;
// import com.uth.fms.customer.entity.Customer;
// import com.uth.fms.order.entity.Order;
import com.uth.fms.common.enums.BomType;
import com.uth.fms.common.enums.ProductionOrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "production_orders")
public class ProductionOrder extends BaseEntity {

    @Column(unique = true, nullable = false, length = 30)
    private String code; // LSX-2026-0001

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "order_id", nullable = false)
    // private Order order;

    @Column(name = "bom_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private BomType bomType; // AUTO, MANUAL

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private ProductionOrderStatus status;

    @Column(name = "planned_start_date")
    private LocalDate plannedStartDate;

    @Column(name = "planned_end_date")
    private LocalDate plannedEndDate;

    @Column(precision = 15, scale = 2, name = "actual_material_cost")
    private BigDecimal actualMaterialCost = BigDecimal.ZERO;

    @Column(precision = 15, scale = 2, name = "actual_labor_cost")
    private BigDecimal actualLaborCost = BigDecimal.ZERO;

    @Column(precision = 15, scale = 2, name = "actual_waste_cost")
    private BigDecimal actualWasteCost = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}