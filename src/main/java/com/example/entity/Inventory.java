package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Inventory extends BaseEntity {
    private int quantity;

    @OneToOne(mappedBy = "stock")
    private Car car;
}
