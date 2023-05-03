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
public class SaleStaff extends BaseEntity {
    private String first_name;
    private String last_name;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

//    @OneToMany(mappedBy = "orders")
//    private List<OrderDetail> orders;

}
