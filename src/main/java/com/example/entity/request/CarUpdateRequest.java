package com.example.entity.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarUpdateRequest {

    private Long id;
    private Double price;
    private Long brandId;
    private Long manufacturerLocationId;
    private Long carModelId;
    private Long categoryId;
    private Long branchId;
    private Long inventoryId;
    private Long carFeatureId;



}
