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

    private String brand;
    private String model;
    private String year;
    private String color;
    private int stock;
    private double price;

    @ManyToOne
    @JoinColumn(name = "branch_name_id")
    private Branch branch_name;

    @OneToMany(mappedBy = "cars", cascade=CascadeType.ALL, orphanRemoval = true)
    List<OrderDetail> orderDetailsList;
}
