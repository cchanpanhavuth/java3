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
public class OrderCars extends BaseEntity {
    @Column(name= "Total_Amount", nullable = false)
    private Double totalAmount;
    @Column(name = "Discount", nullable = false)
    private Double discount;
    @OneToMany(mappedBy = "orderCars", cascade=CascadeType.ALL, orphanRemoval = true)
    List<OrderDetail> orderDetailsList; // It's not column in this table
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private SaleStaff saleStaff;
}
