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
@Table

public class CarFeatures extends BaseEntity {
    private String featureDescription;

    @ManyToMany(mappedBy = "carFeatures")
    private List<Customer> customers;

    @PreRemove
    private void removeCustomerFromCarFeature() {
        for (Customer c : customers) {
            c.getCarFeatures().remove(this);
        }
    }
}
