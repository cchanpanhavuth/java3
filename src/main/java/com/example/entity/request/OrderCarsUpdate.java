package com.example.entity.request;

import lombok.Data;

@Data
public class OrderCarsUpdate {
    private Long id;
    private Long customer_id;
    private Long staff_id;
    private Double total;
    private Double discount;
}
