package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
<<<<<<< HEAD
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
public class CarModel extends BaseEntity {
    @OneToMany(mappedBy = "carModel")
    private List<Car> carModel;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;
=======

public class CarModel extends BaseEntity {
>>>>>>> c471e53 (Additional Tables)
}
