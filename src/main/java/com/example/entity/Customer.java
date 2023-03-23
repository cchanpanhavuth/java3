package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Customer extends BaseEntity {
    private String name;

    @Column(name = "Customer_Number", length = 100, nullable = false, unique = true)
    private String phone;

    @OneToMany(mappedBy = "orders_name")
    private List<OrderDetail> orders_name;

    @OneToMany(mappedBy = "orders_phone")
    private List<OrderDetail> orders_phone;

}
