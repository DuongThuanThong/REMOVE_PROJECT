package com.uth.fms.production.entity;

import com.uth.fms.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

// import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "blocked_reports")
public class BlockedReport extends BaseEntity {

    @Column(name = "task_id", nullable = false)
    Long taskId;

    @Column(name = "reason_note", nullable = false, columnDefinition = "TEXT")
    String reasonNote;

    @Column(name = "resolve_type", length = 20)
    String resolveType;

    @Column(name = "reported_by", nullable = false)
    Long reportedBy;

    @Column(name = "resolved_by")
    Long resolvedBy;

    @Column(name = "resolve_note", columnDefinition = "TEXT")
    String resolveNote;
}
