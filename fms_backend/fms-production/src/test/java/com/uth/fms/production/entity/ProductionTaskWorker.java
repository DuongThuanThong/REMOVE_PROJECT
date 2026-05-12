package com.uth.fms.production.entity;

import com.uth.fms.common.entity.BaseEntity;
// import com.uth.fms.user.entity.User;
// import com.uth.fms.worker.entity.Worker;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "production_task_workers")
public class ProductionTaskWorker extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private ProductionTask task;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "worker_id", nullable = false)
    // private Worker worker;

    @Column(name = "role_in_task", length = 50)
    private String roleInTask;

    @Column(nullable = false, precision = 6, scale = 2, name = "hours_worked")
    private BigDecimal hoursWorked;

    @Column(nullable = false, precision = 10, scale = 2, name = "rate_per_hour")
    private BigDecimal ratePerHour;

    @Column(nullable = false, precision = 12, scale = 2, name = "labor_cost")
    private BigDecimal laborCost;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "recorded_by", nullable = false)
    // private User recordedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}