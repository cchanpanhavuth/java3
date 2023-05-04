package com.example.entity.request;

import lombok.Data;

@Data
public class InventoryUpdateReq {
    private Long id;
    private Integer quantity;
    private Double importPrice;
}
