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
@Table(name = "materials")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Material extends BaseEntity {

    @Column(name = "material_code", nullable = false, unique = true)
    String materialCode;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String unit;

    @Column(name = "current_stock", nullable = false)
    BigDecimal currentStock;

    @Column(name = "reserved_stock", nullable = false)
    BigDecimal reservedStock;

    @Column(name = "min_stock", nullable = false)
    BigDecimal minStock;

    @Column(name = "unit_price", nullable = false)
    BigDecimal unitPrice;

    @Column(nullable = false)
    String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    MaterialCategory category;
}
