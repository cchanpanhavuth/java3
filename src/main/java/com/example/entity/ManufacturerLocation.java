package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ManufacturerLocation extends BaseEntity {
    private String country;
    private Date MFG_date;

}
