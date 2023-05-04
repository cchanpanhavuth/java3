package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderDetail extends BaseEntity {
//    @Column(name = "Unit_Price", nullable = false)
//    private Double unitPrice;
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name= "order_id")
    private OrderCars orderCars;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car cars;









}
