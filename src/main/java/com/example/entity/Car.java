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
    private Branch branch_name;

    @OneToMany(mappedBy = "cars", cascade=CascadeType.ALL, orphanRemoval = true)
    List<OrderDetail> orderDetailsList;

    private Double price;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand_name;

    @ManyToOne
    @JoinColumn(name = "ManufacturerLocation_id")
    private ManufacturerLocation Country;

    @ManyToOne
    @JoinColumn(name = "carModel")
    private CarModel model_name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryName;

    @OneToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory_id;

    @ManyToOne
    @JoinColumn(name = "car_features")
    private CarFeatures features_id;
}
