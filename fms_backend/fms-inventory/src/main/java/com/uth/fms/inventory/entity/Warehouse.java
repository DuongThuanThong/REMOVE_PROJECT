package com.uth.fms.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Warehouse {

    @Column(nullable = false)
    String name;

    String location;

    @Column(name = "is_active")
    Boolean active;
}
