package com.example.entity.request;

import lombok.Data;

@Data
public class OrderCarDetailReq {
    private Long car_id;
    private Long order_id;
    private Double unitPrice;
    private Integer quantity;
}
