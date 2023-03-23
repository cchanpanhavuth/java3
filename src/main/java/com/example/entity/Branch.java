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
public class Branch extends BaseEntity {

    @OneToMany(mappedBy = "branch_name")
    private List<Car> cars;

    @Column(name = "Street_Name", length = 100, nullable = false, unique = true)
    private String streetName;

    @Column(name = "House_Number", length = 100, nullable = false, unique = true)
    private String houseNo;
    private String city;




}
