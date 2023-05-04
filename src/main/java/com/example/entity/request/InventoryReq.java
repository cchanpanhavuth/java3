package com.example.entity.request;

import com.example.entity.Car;
import lombok.Data;

@Data
public class InventoryReq {
    private Integer quantity;
    private Double importPrice;
}
