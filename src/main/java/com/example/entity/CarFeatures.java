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

    @OneToMany(mappedBy= "features_id")
    private List<Car> cars;

    @ManyToMany(mappedBy = "carFeatures")
    private List<Customer> customers;


}
