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
    private ManufacturerLocation ManufacturerLocation;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category_name;

    @ManyToOne
    @JoinColumn(name = "branch_name_id")
    private Branch branch_name;

    @OneToMany(mappedBy = "cars", cascade=CascadeType.ALL, orphanRemoval = true)
    List<OrderDetail> orderDetailsList;
}
