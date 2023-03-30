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
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand_name;

    @ManyToOne
    @JoinColumn(name = "ManufacturerLocation_id")
    private ManufacturerLocation manufacturer_location;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel model_name;

    @ManyToOne
    @JoinColumn(name = "branch_name_id")
    private Branch branch_name;

    @ManyToMany(mappedBy = "cars")
    private List<OrderDetail> orders;

    @OneToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "car_features")
    private CarFeatures features_id;
}
