package com.uth.fms.user.entity;

import com.uth.fms.common.entity.BaseEntity;
import com.uth.fms.common.enums.RolesEnum;
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
@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false)
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    RolesEnum roles;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    Boolean enabled = true;

}
