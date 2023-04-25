package com.example.entity;

import com.example.entity.enums.GenderEnum;
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
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "gender" , length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum genderEnum;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phoneNumber", length = 100, nullable = false, unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<OrderCars> orderCars;

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
