package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @ManyToMany(mappedBy = "cars")
    private List<OrderDetail> orders;
}
