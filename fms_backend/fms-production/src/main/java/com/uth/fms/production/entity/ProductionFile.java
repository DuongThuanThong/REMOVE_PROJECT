package com.uth.fms.production.entity;

import com.uth.fms.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "production_files")
public class ProductionFile extends BaseEntity {

    @Column(name = "production_order_id", nullable = false)
    Long productionOrderId;

    @Column(name = "task_id")
    Long taskId;

    @Column(name = "file_type", nullable = false, length = 20)
    String fileType;

    @Column(name = "file_url", nullable = false, length = 500)
    String fileUrl;

    @Builder.Default
    @Column(nullable = false)
    Integer version = 1;

    @Builder.Default
    @Column(name = "is_latest", nullable = false)
    Boolean isLatest = true;

    @Column(name = "approval_status", length = 20)
    String approvalStatus;

    @Column(name = "uploaded_by", nullable = false)
    Long uploadedBy;
}
