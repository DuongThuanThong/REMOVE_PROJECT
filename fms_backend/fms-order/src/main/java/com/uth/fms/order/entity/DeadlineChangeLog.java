package com.uth.fms.order.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "deadline_change_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeadlineChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "order_id", nullable = false)
    Long orderId;

    @Column(name = "old_deadline", nullable = false)
    LocalDate oldDeadline;

    @Column(name = "new_deadline", nullable = false)
    LocalDate newDeadline;

    @Column(name = "reason_note", nullable = false, columnDefinition = "TEXT") 
    String reasonNote;

    @Column(name = "changed_by", nullable = false)
    Long changedBy;

    @Column(name = "changed_at", updatable = false) 
    java.time.LocalDateTime changedAt;

    @PrePersist
    protected void onCreate() {
        if (this.changedAt == null) {
            this.changedAt = java.time.LocalDateTime.now();
        }
    }
}
