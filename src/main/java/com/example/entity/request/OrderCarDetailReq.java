package com.example.entity.request;

import lombok.Data;

@Data
public class OrderCarDetailReq {
    private Long car_id;
    private Integer quantity;
}
