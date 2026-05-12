package com.uth.fms.customer.entity;

import com.uth.fms.common.entity.BaseEntity;
// import com.uth.fms.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(columnDefinition = "text")
    private String address;

    @Column(length = 20, name = "tax_code")
    private String taxCode;

    @Column(columnDefinition = "text")
    private String note;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "assigned_sale", foreignKey = @ForeignKey(name =
    // "fk_customers_assigned_sale"))
    // private User assignedSale;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "created_by", foreignKey = @ForeignKey(name =
    // "fk_customers_created_by"))
    // private User createdBy;

    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}