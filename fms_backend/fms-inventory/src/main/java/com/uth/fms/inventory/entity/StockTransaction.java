package com.uth.fms.inventory.entity;

import com.uth.fms.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_transactions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockTransaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", nullable = false)
    Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    Warehouse warehouse;

    @Column(name = "transaction_type", nullable = false)
    String transactionType;

    @Column(nullable = false)
    BigDecimal quantity;

    @Column(name = "reference_type")
    String referenceType;

    @Column(name = "reference_id")
    Long referenceId;

    @Column(name = "performed_by")
    Long performedById;

    @Column(name = "lot_number")
    String lotNumber;

    @Column(name = "unit_cost")
    BigDecimal unitCost;
}
