package com.uth.fms.user.entity;

import com.uth.fms.common.entity.BaseEntity;
import com.uth.fms.common.enums.RolesEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    @Column(nullable = false)
    Enum<RolesEnum> roles;

    @Column(name = "is_active", nullable = false)
    Boolean enabled = true;

}
