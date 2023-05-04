package com.example.entity.request;


import com.example.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarReq {

    private String year;
    private String color;
    private double price;
    private Long inventoryId;
    private Long categoryId;
    private Long featureId;
    private Long modelId;
    private Long branchId;
    private Long brandId;
    private Long manufactureId;



}
