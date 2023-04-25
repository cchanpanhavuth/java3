package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

public class CarFeatures extends BaseEntity {
    private String featureDescription;

    @OneToMany(mappedBy= "features_id")
    private List<Car> cars;

    @ManyToMany(mappedBy = "carFeatures")
    private List<Customer> customers;

}
