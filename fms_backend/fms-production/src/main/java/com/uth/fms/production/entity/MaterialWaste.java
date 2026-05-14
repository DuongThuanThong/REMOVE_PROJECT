package com.uth.fms.production.entity;

import com.uth.fms.production.enums.MaterialWasteType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "material_waste")
@EntityListeners(AuditingEntityListener.class)
public class MaterialWaste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "production_order_id", nullable = false)
    Long productionOrderId;

    @Column(name = "task_id")
    Long taskId;

    @Column(name = "material_id", nullable = false)
    Long materialId;

    @Column(name = "qty_wasted", nullable = false)
    BigDecimal qtyWasted;

    @Enumerated(EnumType.STRING)
    @Column(name = "waste_type", nullable = false, length = 30)
    MaterialWasteType wasteType;

    @CreatedDate
    @Column(name = "create_at", updatable = false, nullable = false)
    LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_at", nullable = false)
    LocalDateTime updateTime;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    String updatedBy;

    @Column(name = "is_deleted")
    Boolean deleteFlag;
}
