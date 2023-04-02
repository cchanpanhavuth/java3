package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
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
public class CarModel extends BaseEntity {
    @OneToMany(mappedBy = "carModel")
    private List<Car> carModel;
}
