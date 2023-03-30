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
public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;

    @Column(name = "phone_number", length = 100, nullable = false, unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "orders_id")
    private List<OrderDetail> orders_id;

    @ManyToMany
    @JoinTable( name = "customer_pref",
            joinColumns = @JoinColumn(
                    name = "Customer_id" , referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "CarFeature_id" , referencedColumnName = "id"
            )
    )
    private List<CarFeatures> carFeatures;

}
