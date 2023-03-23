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

    private Double totalPrice;

    @ManyToMany
    @JoinTable( name = "order_car",
            joinColumns = @JoinColumn(
                    name = "orderDetail_id" , referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "car_id" , referencedColumnName = "id"
            )
    )
    private List<Car> cars;

    @ManyToOne
    @JoinColumn(name = "customer_name")
    private Customer orders_name;

    @ManyToOne
    @JoinColumn(name = "customer_phone")
    private Customer orders_phone;

    @ManyToOne
    @JoinColumn(name = "sales_staff")
    private SaleStaff orders;
}
