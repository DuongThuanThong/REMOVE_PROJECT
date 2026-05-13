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
@Table(name = "qc_logs")
public class QcLog extends BaseEntity {

    @Column(name = "task_id", nullable = false)
    Long taskId;

    @Column(nullable = false, length = 10)
    String result;

    @Column(name = "fail_type", length = 20)
    String failType;

    @Column(nullable = false, columnDefinition = "TEXT")
    String note;
}
