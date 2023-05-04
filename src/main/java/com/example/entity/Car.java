package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car extends BaseEntity {

    private String year;
    private String color;

    @ManyToOne
    @JoinColumn(name = "branch_name_id")
    private Branch branchId;

    private Double price;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brandId;

    @ManyToOne
    @JoinColumn(name = "ManufacturerLocation_id")
    private ManufacturerLocation manufactureId;

    @ManyToOne
    @JoinColumn(name = "Model_id")
    private CarModel modelId;

    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category categoryId;

    @OneToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventoryId;

    @ManyToOne
    @JoinColumn(name = "features_id")
    private CarFeatures featuresId;
}
