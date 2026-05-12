package com.uth.fms.common.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass // Không cần bảng riêng trong DB, chỉ là class cha để kế thừa
@EntityListeners(AuditingEntityListener.class) // Khởi tạo Listener để lắng nghe tự động điền ngày giờ và user
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updateTime;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    LocalDateTime createTime;

    @CreatedBy
    @Column(name = "created_by")
    Long createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    Long updatedBy;

    @Column(name = "is_deleted")
    @Builder.Default
    Boolean deleteFlag = false;

}
